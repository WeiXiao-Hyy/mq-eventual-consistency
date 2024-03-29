package com.alipay.orderservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionLog {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column transaction_log.id
     *
     * @mbg.generated
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column transaction_log.business
     *
     * @mbg.generated
     */
    private String business;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column transaction_log.foreign_key
     *
     * @mbg.generated
     */
    private String foreignKey;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column transaction_log.id
     *
     * @return the value of transaction_log.id
     * @mbg.generated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column transaction_log.id
     *
     * @param id the value for transaction_log.id
     * @mbg.generated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column transaction_log.business
     *
     * @return the value of transaction_log.business
     * @mbg.generated
     */
    public String getBusiness() {
        return business;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column transaction_log.business
     *
     * @param business the value for transaction_log.business
     * @mbg.generated
     */
    public void setBusiness(String business) {
        this.business = business == null ? null : business.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column transaction_log.foreign_key
     *
     * @return the value of transaction_log.foreign_key
     * @mbg.generated
     */
    public String getForeignKey() {
        return foreignKey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column transaction_log.foreign_key
     *
     * @param foreignKey the value for transaction_log.foreign_key
     * @mbg.generated
     */
    public void setForeignKey(String foreignKey) {
        this.foreignKey = foreignKey == null ? null : foreignKey.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table transaction_log
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", business=").append(business);
        sb.append(", foreignKey=").append(foreignKey);
        sb.append("]");
        return sb.toString();
    }
}