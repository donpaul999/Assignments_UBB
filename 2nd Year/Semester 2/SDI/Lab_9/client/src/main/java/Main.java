import config.ClientConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ui.Console;
import ui.services.CommandService;


public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ClientConfig.class);

        CommandService commandService = context.getBean(CommandService.class);
        commandService.init(context);

        try {
            commandService.loadCommands();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Console console = new Console(context);
        console.show();
    }

}
