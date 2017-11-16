package stateCapital;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

public class StateCapitalDemo {

    public static void main(String[] args) throws InterruptedException {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
        DynamoDBMapper mapper = new DynamoDBMapper(client);

        StateCapital washington = new StateCapital();
        washington.setStateName("Washington");
        washington.setStateCapital("Olympia");
        washington.setPopulation(200);
        washington.setTestValue("Test for washington");

        mapper.save(washington);

        StateCapital readWashington = mapper.load(StateCapital.class, "Washington");

        System.out.println("StateName: " + readWashington.getStateName());
        System.out.println("StateCapital: " + readWashington.getStateCapital());
        System.out.println("Population: " + readWashington.getPopulation());
        System.out.println("TestValue: " + readWashington.getTestValue());


        readWashington.setPopulation(1000);

        mapper.save(readWashington);
    }
}
