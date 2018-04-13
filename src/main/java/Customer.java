import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private String addres;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Account> accounts;

    public Customer()
    {
    }

    public Customer(String name, String surname, String addres)
    {
        this.customerId = 0;
        this.name = name;
        this.surname = surname;
        this.addres = addres;
        accounts = new ArrayList<Account>();
    }

    public void addToList(Account account)
    {
        accounts.add(new Account(this, account.getAccount_type()));
    }

    public int getCustomerId()
    {
        return customerId;
    }

    public void setCustomerId(int customerId)
    {
        this.customerId = customerId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSurname()
    {
        return surname;
    }

    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    public String getAddres()
    {
        return addres;
    }

    public void setAddres(String addres)
    {
        this.addres = addres;
    }

    public List<Account> getAccounts()
    {
        return accounts;
    }

    public void setAccounts(List<Account> accounts)
    {
        this.accounts = accounts;
    }

    @Override
    public String toString()
    {
        return "Customer{" +
                "customerId=" + customerId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", addres='" + addres + '\'' +
                ", accounts=" + accounts +
                '}';
    }
}
