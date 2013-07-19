
import com.yomari.telecom.model.Contact;
import com.yomari.telecom.service.ContactService;
import java.util.List;
import org.springframework.context.support.GenericXmlApplicationContext;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author pushpa
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:DaoContext.xml");
        ctx.refresh();

        ContactService contactService = ctx.getBean("contactService", ContactService.class);

        // Testing findAll() method
        List<Contact> contacts = contactService.findAll();

        for (Contact contact : contacts) {
            System.out.println(contact);
        }

        // Testing save() method
        Contact contact = contactService.findById(1l);
        contact.setFirstName("Ramesh");
        contactService.save(contact);
        System.out.println("Contact saved successfully");

        // Testing countAll() method
        System.out.println("Contact count: " + contactService.countAll());
    }
}
