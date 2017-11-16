package pagination;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactManager {
    public static void main(String[] args) {
        final AmazonDynamoDB dynamoDBClient = AmazonDynamoDBClientBuilder.standard().build();
        final DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(dynamoDBClient);

        final ContactDao contactDao = new ContactDao(dynamoDBClient, dynamoDBMapper);

//        contactDao.createTable();

//        List<Contact> contacts = contactDao.getAllContacts();
//        while (!contacts.isEmpty()) {
//            System.out.println("New page ...");
//            for (Contact contact : contacts) {
//                StringBuilder stringBuilder = new StringBuilder();
//                stringBuilder.append("First name: ");
//                stringBuilder.append(contact.getFirstName());
//                stringBuilder.append(", Last name: ");
//                stringBuilder.append(contact.getLastName());
//                if (contact.getPhones() != null) {
//                    stringBuilder.append(", Phones: ");
//                    for (String phone : contact.getPhones()) {
//                        stringBuilder.append(phone);
//                        stringBuilder.append(", ");
//                    }
//                }
//                System.out.println(stringBuilder.toString());
//            }
//            Contact lastContact =  contacts.get(contacts.size() - 1);
//
//            contacts = contactDao.getAllContacts(lastContact.getFirstName(), lastContact.getLastName());
//        }
//        List<Contact> contacts = contactDao.getAllContacts("Joe", "Betts");


        /////// Version
//        Contact newContact = new Contact();
//        newContact.setFirstName("Navid");
//        newContact.setLastName("Fallah");
//        contactDao.saveContact(newContact);


        Contact loadedContact = contactDao.loadContact("Navid", "Fallah");

//        Set<String> emails = new HashSet();
//        emails.add("nfallah@gmail.com");
//        loadedContact.setEmails(emails);

        Set<String> phones = new HashSet();
        phones.add("775");
        loadedContact.setPhones(phones);

        contactDao.saveContact(loadedContact);
    }
}