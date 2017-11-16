package stateinfo;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "state-info-nfallah")
public class StatePop {
    private String stateName;
    private String type;
    private int population;

    @DynamoDBHashKey(attributeName = "StateName")
    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    @DynamoDBRangeKey(attributeName = "Type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @DynamoDBAttribute(attributeName = "Population")
    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }
}
