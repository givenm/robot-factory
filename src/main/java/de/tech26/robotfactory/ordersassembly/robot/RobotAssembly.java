package de.tech26.robotfactory.ordersassembly.robot;

import de.tech26.robotfactory.enums.ErrorCodesEnum;
import de.tech26.robotfactory.enums.RobotPartType;
import de.tech26.robotfactory.exceptions.GlobalRuntimeException;
import de.tech26.robotfactory.model.Order;
import de.tech26.robotfactory.model.OrderBuilder;
import de.tech26.robotfactory.model.Product;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

class RobotAssembly {

    public static class Builder {
        private final String customerId;
        private Product face;
        private Product arms;
        private Product mobility;
        private Product material;

        public Builder(String customerId) {
            this.customerId = customerId;
        }

        public Builder withComponents(List<Product> components) {
            components.forEach(product -> {
                RobotPartType robotPartType = RobotPartType.valueOf(product.getType());
                if (robotPartType == RobotPartType.FACE) {
                    this.face = product;
                } else if (robotPartType == RobotPartType.ARMS) {
                    this.arms = product;
                } else if (robotPartType == RobotPartType.MOBILITY) {
                    this.mobility = product;
                } else if (robotPartType == RobotPartType.MATERIAL) {
                    this.material = product;
                }
            });
            return this;
        }

        public Order build() {
            BigDecimal total = new BigDecimal(0);
            total = orderComponent(total, face, "Face");
            total = orderComponent(total, arms, "Arms");
            total = orderComponent(total, mobility, "Mobility");
            total = orderComponent(total, material, "Material");

            return new OrderBuilder()
                    .withOrderedAt(new Date())
                    .withId(UUID.randomUUID().toString())
                    .withCustomerId(customerId)
                    .withTotal(total)
                    .withProductIds(Arrays.asList(face.getId(), arms.getId(), mobility.getId(), material.getId()))
                    .createOrder();
        }

        @NotNull
        private BigDecimal orderComponent(BigDecimal total, Product product, String productType) {
            if (product != null) {
                if (product.getQuantity() > 0) {
                    return total.add(product.getPrice());
                }
                throw new GlobalRuntimeException(ErrorCodesEnum.PRODUCT_OUT_OF_STOCK, String.format("%s is out of stock. Please check again later", product.getName()));
            }
            throw new GlobalRuntimeException(ErrorCodesEnum.PRODUCT_OUT_OF_STOCK, String.format("%s component is missing to fulfil the order", productType));
        }

    }
}
