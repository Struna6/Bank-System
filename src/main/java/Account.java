import javax.persistence.*;

@Entity
@Table(name = "accounts")
public class Account
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountId;
    @ManyToOne
    @JoinColumn(name="customers_id", foreignKey = @ForeignKey(name="CUSTOMER_ID_FK"))
    private Customer customer;
    @Enumerated(EnumType.STRING)
    private Account_Type account_type;

    public Account()
    {
    }


    public Account(Customer customer, Account_Type account_type)
    {
        this.accountId = 0;
        this.account_type = account_type;
        this.customer=customer;
    }

    public Customer getCustomer()
    {
        return customer;
    }

    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }

    public Account_Type getAccount_type()
    {
        return account_type;
    }

    public void setAccount_type(Account_Type account_type)
    {
        this.account_type = account_type;
    }

    public int getAccountId()
    {
        return accountId;
    }

    public void setAccountId(int accountId)
    {
        this.accountId = accountId;
    }

    @Override
    public String toString()
    {
        return "Account{nazwa='" + account_type + '\'' +
                '}';
    }
}
