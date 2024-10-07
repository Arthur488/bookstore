package practise.models.order;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import practise.models.AuditModel;

import java.math.BigDecimal;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "orders")
public class Order extends AuditModel {

    private BigDecimal totalPrice;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List <OrderItem> orderItems;

    public void setOrderItems(List <OrderItem> orderItems) {
        this.orderItems = orderItems;
        setTotalPrice();
    }

    public void setTotalPrice() {
        totalPrice = BigDecimal.ZERO;
        orderItems.forEach(orderItem -> totalPrice = totalPrice.add(orderItem.getSubtotal()));
    }

}
