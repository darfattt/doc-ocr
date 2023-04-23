package com.darfat.docreaderapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.domain.Persistable;

/**
 * A FormPengeluaranBarang.
 */
@JsonIgnoreProperties(value = { "new" })
@Entity
@Table(name = "form_pengeluaran_barang")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class FormPengeluaranBarang extends AbstractAuditingEntity implements Serializable, Persistable<String> {

    private static final long serialVersionUID = 1L;

    @Size(max = 50)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", length = 50, nullable = false)
    @Id
    private String id;

    @Size(max = 2)
    @Column(name = "status", length = 2)
    private String status;

    @NotNull
    @Column(name = "active", nullable = false)
    private Boolean active;

    @Size(max = 100)
    @Column(name = "orderStatus", length = 2)
    private String orderStatus;

    @Size(max = 500)
    @Column(name = "remarks", length = 500)
    private String remarks;

    @Size(max = 65353)
    @Column(name = "contents", length = 65353)
    private String contents;

    @Size(max = 100)
    @Column(name = "branch", length = 100)
    private String branch;

    @Size(max = 100)
    @Column(name = "document_title", length = 100)
    private String documentTitle;

    @Size(max = 100)
    @Column(name = "document_number", length = 100)
    private String documentNumber;

    @Size(max = 1000)
    @Column(name = "recipient_address", length = 1000)
    private String recipientAddress;

    @Size(max = 100)
    @Column(name = "npwp", length = 100)
    private String npwp;

    @Size(max = 100)
    @Column(name = "warehouse_source", length = 100)
    private String warehouseSource;

    @Size(max = 100)
    @Column(name = "document_source", length = 100)
    private String documentSource;

    @Size(max = 100)
    @Column(name = "reference", length = 100)
    private String reference;


    @Size(max = 100)
    @Column(name = "date", length = 100)
    private String date;

    @Size(max = 100)
    @Column(name = "product_description", length = 100)
    private String productDescription;

    @Size(max = 100)
    @Column(name = "source_location", length = 100)
    private String sourceLocation;

    @Size(max = 100)
    @Column(name = "lot_no", length = 100)
    private String lotNo;

    @Column(name = "quantity")
    private Float quantity;

    @Column(name = "amount", precision = 21, scale = 2)
    private BigDecimal amount;

    @Size(max = 100)
    @Column(name = "source_destination", length = 100)
    private String sourceDestination;

    @Size(max = 100)
    @Column(name = "armada_name", length = 100)
    private String armadaName;

    @Size(max = 100)
    @Column(name = "armada_number", length = 100)
    private String armadaNumber;

    @Transient
    private boolean isPersisted;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public FormPengeluaranBarang id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return this.status;
    }

    public FormPengeluaranBarang status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getActive() {
        return this.active;
    }

    public FormPengeluaranBarang active(Boolean active) {
        this.setActive(active);
        return this;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public FormPengeluaranBarang remarks(String remarks) {
        this.setRemarks(remarks);
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getContents() {
        return this.contents;
    }

    public FormPengeluaranBarang contents(String contents) {
        this.setContents(contents);
        return this;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getBranch() {
        return this.branch;
    }

    public FormPengeluaranBarang branch(String branch) {
        this.setBranch(branch);
        return this;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getDocumentTitle() {
        return this.documentTitle;
    }

    public FormPengeluaranBarang documentTitle(String documentTitle) {
        this.setDocumentTitle(documentTitle);
        return this;
    }

    public void setDocumentTitle(String documentTitle) {
        this.documentTitle = documentTitle;
    }

    public String getDocumentNumber() {
        return this.documentNumber;
    }

    public FormPengeluaranBarang documentNumber(String documentNumber) {
        this.setDocumentNumber(documentNumber);
        return this;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getRecipientAddress() {
        return this.recipientAddress;
    }

    public FormPengeluaranBarang recipientAddress(String recipientAddress) {
        this.setRecipientAddress(recipientAddress);
        return this;
    }

    public void setRecipientAddress(String recipientAddress) {
        this.recipientAddress = recipientAddress;
    }

    public String getNpwp() {
        return this.npwp;
    }

    public FormPengeluaranBarang npwp(String npwp) {
        this.setNpwp(npwp);
        return this;
    }

    public void setNpwp(String npwp) {
        this.npwp = npwp;
    }

    public String getWarehouseSource() {
        return this.warehouseSource;
    }

    public FormPengeluaranBarang warehouseSource(String warehouseSource) {
        this.setWarehouseSource(warehouseSource);
        return this;
    }

    public void setWarehouseSource(String warehouseSource) {
        this.warehouseSource = warehouseSource;
    }

    public String getDocumentSource() {
        return this.documentSource;
    }

    public FormPengeluaranBarang documentSource(String documentSource) {
        this.setDocumentSource(documentSource);
        return this;
    }

    public void setDocumentSource(String documentSource) {
        this.documentSource = documentSource;
    }

    public String getReference() {
        return this.reference;
    }

    public FormPengeluaranBarang reference(String reference) {
        this.setReference(reference);
        return this;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }


    public String getDate() {
        return this.date;
    }

    public FormPengeluaranBarang date(String date) {
        this.setDate(date);
        return this;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getProductDescription() {
        return this.productDescription;
    }

    public FormPengeluaranBarang productDescription(String productDescription) {
        this.setProductDescription(productDescription);
        return this;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getSourceLocation() {
        return this.sourceLocation;
    }

    public FormPengeluaranBarang sourceLocation(String sourceLocation) {
        this.setSourceLocation(sourceLocation);
        return this;
    }

    public void setSourceLocation(String sourceLocation) {
        this.sourceLocation = sourceLocation;
    }

    public String getLotNo() {
        return this.lotNo;
    }

    public FormPengeluaranBarang lotNo(String lotNo) {
        this.setLotNo(lotNo);
        return this;
    }

    public void setLotNo(String lotNo) {
        this.lotNo = lotNo;
    }

    public Float getQuantity() {
        return this.quantity;
    }

    public FormPengeluaranBarang quantity(Float quantity) {
        this.setQuantity(quantity);
        return this;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public FormPengeluaranBarang amount(BigDecimal amount) {
        this.setAmount(amount);
        return this;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getSourceDestination() {
        return this.sourceDestination;
    }

    public FormPengeluaranBarang sourceDestination(String sourceDestination) {
        this.setSourceDestination(sourceDestination);
        return this;
    }

    public void setSourceDestination(String sourceDestination) {
        this.sourceDestination = sourceDestination;
    }

    public String getArmadaName() {
        return this.armadaName;
    }

    public FormPengeluaranBarang armadaName(String armadaName) {
        this.setArmadaName(armadaName);
        return this;
    }

    public void setArmadaName(String armadaName) {
        this.armadaName = armadaName;
    }

    public String getArmadaNumber() {
        return this.armadaNumber;
    }

    public FormPengeluaranBarang armadaNumber(String armadaNumber) {
        this.setArmadaNumber(armadaNumber);
        return this;
    }

    public void setArmadaNumber(String armadaNumber) {
        this.armadaNumber = armadaNumber;
    }

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public FormPengeluaranBarang setIsPersisted() {
        this.isPersisted = true;
        return this;
    }

    @PostLoad
    @PostPersist
    public void updateEntityState() {
        this.setIsPersisted();
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FormPengeluaranBarang)) {
            return false;
        }
        return id != null && id.equals(((FormPengeluaranBarang) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FormPengeluaranBarang{" +
            "id=" + getId() +
            ", status='" + getStatus() + "'" +
            ", active='" + getActive() + "'" +
            ", remarks='" + getRemarks() + "'" +
            ", contents='" + getContents() + "'" +
            ", branch='" + getBranch() + "'" +
            ", documentTitle='" + getDocumentTitle() + "'" +
            ", documentNumber='" + getDocumentNumber() + "'" +
            ", recipientAddress='" + getRecipientAddress() + "'" +
            ", npwp='" + getNpwp() + "'" +
            ", warehouseSource='" + getWarehouseSource() + "'" +
            ", documentSource='" + getDocumentSource() + "'" +
            ", reference='" + getReference() + "'" +
            ", date='" + getDate() + "'" +
            ", productDescription='" + getProductDescription() + "'" +
            ", sourceLocation='" + getSourceLocation() + "'" +
            ", lotNo='" + getLotNo() + "'" +
            ", quantity=" + getQuantity() +
            ", amount=" + getAmount() +
            ", sourceDestination='" + getSourceDestination() + "'" +
            ", armadaName='" + getArmadaName() + "'" +
            ", armadaNumber='" + getArmadaNumber() + "'" +
            "}";
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
