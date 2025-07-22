import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "wallets")
public class Wallet {
    @Id private String id;
    private Long userId;
    private BigDecimal balance;

    protected Wallet() {}
    public Wallet(Long userId) {
        this.id = java.util.UUID.randomUUID().toString();
        this.userId = userId;
        this.balance = BigDecimal.ZERO;
    }

    // getters / setters omitted
}
