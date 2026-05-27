package enalab.board_ai.infrastructure.api;

import enalab.board_ai.application.CreateTaskUseCase;
import enalab.board_ai.application.input.TaskInput;
import enalab.board_ai.infrastructure.api.request.TaskRequest;
import enalab.board_ai.infrastructure.api.response.TaskResponse;
import org.springframework.ai.audio.transcription.TranscriptionModel;
import org.springframework.ai.audio.tts.TextToSpeechModel;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.Charset;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final CreateTaskUseCase createTaskUseCase;

    private final TranscriptionModel transcriptionModel;
    private final ChatClient chatClient;
    private final TextToSpeechModel textToSpeechModel;

    public TaskController(CreateTaskUseCase createTaskUseCase,
                          TranscriptionModel transcriptionModel,
                          @Value("classpath:prompt/system-message.st") Resource systemPrompt,
                          ChatClient.Builder chatClient,
                          TextToSpeechModel textToSpeechModel) throws IOException {
        this.createTaskUseCase = createTaskUseCase;
        this.transcriptionModel = transcriptionModel;
        this.chatClient = chatClient
                .defaultSystem(systemPrompt.getContentAsString(Charset.defaultCharset()))
                .defaultTools(createTaskUseCase)
                .build();
        this.textToSpeechModel = textToSpeechModel;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponse createTask(@RequestBody TaskRequest taskRequest){
        return TaskResponse.from(
                createTaskUseCase.execute(
                        TaskInput.toInput(taskRequest)));
    }

    @PostMapping(value = "/ai", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = "audio/mp3")
    ResponseEntity<Resource> transcribe(@RequestParam("file") MultipartFile file) {
        var userMessage = transcriptionModel.transcribe(file.getResource());
        var result = chatClient.prompt().user(userMessage).call().content();

        byte[] audio = textToSpeechModel.call(result);
        var resource = new ByteArrayResource(audio);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment()
                                .filename("audio.mp3")
                                .build()
                                .toString())
                .body(resource);
    }

}
