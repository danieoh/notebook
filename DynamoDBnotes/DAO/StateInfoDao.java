package stateinfo;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import sun.tools.jconsole.Tab;

import java.util.HashMap;
import java.util.Map;

public class StateInfoDao {
    private DynamoDBMapper mapper;
    private DynamoDB dynamoDB;
    private Table table;

    public StateInfoDao(DynamoDBMapper mapper, DynamoDB dynamoDB) {
        this.mapper = mapper;
        this.dynamoDB = dynamoDB;
        table = dynamoDB.getTable("state-info-nfallah");
    }

    public int getStatePopulation(String stateName) {
        StatePop statePop =
            mapper.load(StatePop.class, stateName, "Population");

        return statePop.getPopulation();
    }

    public boolean updateStatePopulation(String stateName, int maxPopulation, int newPopulation) {
        Map<String, String> attributeNames = new HashMap<>();
        attributeNames.put("#pop", "Population");

        Map<String, Object> attributeValues = new HashMap<>();
        attributeValues.put(":newPop", newPopulation);
        attributeValues.put(":maxPop", maxPopulation);
        attributeValues.put(":zero", 0);

        try {
            table.updateItem(
                "StateName",
                stateName,
                "Type",
                "Population",
                "set #pop = :newPop",
                "Population < :maxPop AND Population > :zero",
                attributeNames,
                attributeValues);
            return true;
        } catch (ConditionalCheckFailedException e) {
            // log
            return false;
        }
    }

    public String getStateCapital(String stateName) {
        StateCapital stateCapital =
            mapper.load(StateCapital.class, stateName, "Capital");

        return stateCapital.getCityName();
    }
}
