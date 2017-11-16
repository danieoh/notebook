package pagination;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBVersionAttribute;

import java.util.Set;

@DynamoDBTable(tableName = "contact-book-nfallah")
public class Contact {
    private String firstName;
    private String lastName;
    private Set<String> emails;
    private Set<String> phones;
    private Long version;

    @DynamoDBHashKey
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @DynamoDBRangeKey
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<String> getEmails() {
        return emails;
    }

    public void setEmails(Set<String> emails) {
        this.emails = emails;
    }

    public Set<String> getPhones() {
        return phones;
    }

    public void setPhones(Set<String> phones) {
        this.phones = phones;
    }

    @DynamoDBVersionAttribute
    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
