package stateinfo;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

public class StateInfoDemo {

    public static void main(String[] args) throws InterruptedException {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
        DynamoDB dynamoDB = new DynamoDB(client);
        DynamoDBMapper mapper = new DynamoDBMapper(client);

        StateInfoDao stateInfoDao = new StateInfoDao(mapper, dynamoDB);

        System.out.println(stateInfoDao.getStatePopulation("WA"));

        System.out.println(stateInfoDao.getStateCapital("WA"));

        stateInfoDao.updateStatePopulation(
            "WA",
            500,
            1000);
    }
}