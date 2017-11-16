package stateCapital;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "state-capital-nfallah")
public class StateCapital {

    private String stateName;
    private String stateCapital;
    private int population;
    private String testValue;

    @DynamoDBHashKey(attributeName = "StateName")
    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    @DynamoDBAttribute(attributeName = "Population")
    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    @DynamoDBIgnore
    public String getTestValue() {
        return testValue;
    }

    public void setTestValue(String testValue) {
        this.testValue = testValue;
    }
}
