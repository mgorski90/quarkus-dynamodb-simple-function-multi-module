package pl.mgorski;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import lombok.RequiredArgsConstructor;

import javax.inject.Named;

@Named("sender")
@RequiredArgsConstructor
public class ProcessingLambda implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private static final int SUCCESS_CODE = 200;

    private final JacksonSerializer jacksonSerializer;
    private final MessageService messageService;

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, Context context) {
        String body = request.getBody();
        MessageDTO inputDTO = jacksonSerializer.from(body);
        MessageDTO processedDTO = messageService.send(inputDTO);
        String response = jacksonSerializer.toJson(processedDTO);
        return new APIGatewayProxyResponseEvent()
                .withBody(response)
                .withStatusCode(SUCCESS_CODE);
    }

}
