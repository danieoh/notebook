package pagination;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.QueryOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ContactDao {

    private static final long READ_CAPACITY_UNITS = 1;
    private static final long WRITE_CAPACITY_UNITS = 1;

    private AmazonDynamoDB dynamoDBClient;
    private DynamoDBMapper dynamoDBMapper;

    public ContactDao(AmazonDynamoDB dynamoDBClient, DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBClient = dynamoDBClient;
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public void createTable() {
        final CreateTableRequest createTableRequest = dynamoDBMapper.generateCreateTableRequest(Contact.class);
        createTableRequest.setProvisionedThroughput(
            new ProvisionedThroughput(READ_CAPACITY_UNITS, WRITE_CAPACITY_UNITS));
        dynamoDBClient.createTable(createTableRequest);

        System.out.println("Contact table has been created successfully");
    }

    public void saveContact(Contact contact) {
        dynamoDBMapper.save(contact);
    }

    public Contact loadContact(String firstName, String lastName) {
        return dynamoDBMapper.load(Contact.class, firstName, lastName);
    }

    public List<Contact> getContacts(String firstName) {
        final Map<String, AttributeValue> expressionAttributeValues = new HashMap();
        expressionAttributeValues.put(":firstName", new AttributeValue().withS(firstName));

        final Map<String, AttributeValue> exclusiveStartKey = new HashMap();
        exclusiveStartKey.put("firstName", new AttributeValue().withS(firstName));

        final DynamoDBQueryExpression<Contact> queryExpression = new DynamoDBQueryExpression()
            .withKeyConditionExpression("firstName = :firstName")
            .withExpressionAttributeValues(expressionAttributeValues)
            .withLimit(2);

        return dynamoDBMapper.queryPage(Contact.class, queryExpression).getResults();
    }

    public List<Contact> getContacts(String firstName, String lastName) {
        final Map<String, AttributeValue> expressionAttributeValues = new HashMap();
        expressionAttributeValues.put(":firstName", new AttributeValue().withS(firstName));

        final Map<String, AttributeValue> exclusiveStartKey = new HashMap();
        exclusiveStartKey.put("firstName", new AttributeValue().withS(firstName));
        exclusiveStartKey.put("lastName", new AttributeValue().withS(lastName));

        final DynamoDBQueryExpression<Contact> queryExpression = new DynamoDBQueryExpression()
            .withKeyConditionExpression("firstName = :firstName")
            .withExpressionAttributeValues(expressionAttributeValues)
            .withLimit(2)
            .withExclusiveStartKey(exclusiveStartKey);

        return dynamoDBMapper.queryPage(Contact.class, queryExpression).getResults();
    }


    public List<Contact> getAllContacts() {
        final DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
            .withLimit(3);

        return dynamoDBMapper.scanPage(Contact.class, scanExpression).getResults();
    }

    public List<Contact> getAllContacts(String firstName, String lastName) {
        final Map<String, AttributeValue> exclusiveStartKey = new HashMap();
        exclusiveStartKey.put("firstName", new AttributeValue().withS(firstName));
        exclusiveStartKey.put("lastName", new AttributeValue().withS(lastName));

        final DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
            .withLimit(3)
            .withExclusiveStartKey(exclusiveStartKey);

        return dynamoDBMapper.scanPage(Contact.class, scanExpression).getResults();
    }
}
