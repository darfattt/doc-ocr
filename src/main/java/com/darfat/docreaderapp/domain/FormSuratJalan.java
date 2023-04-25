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
 * A FormSuratJalan.
 */
@JsonIgnoreProperties(value = { "new" })
@Entity
@Table(name = "form_surat_jalan")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class FormSuratJalan extends AbstractAuditingEntity implements Serializable, Persistable<String> {

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

    @Size(max = 500)
    @Column(name = "remarks", length = 500)
    private String remarks;

    @Size(max = 65353)
    @Column(name = "contents", length = 65353)
    private String contents;

    @Size(max = 100)
    @Column(name = "branch", length = 100)
    private String branch;

    @Size(max = 500)
    @Column(name = "document_title", length = 500)
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

    @Column(name = "quantity")
    private Float quantity;

    @Column(name = "amount", precision = 21, scale = 2)
    private BigDecimal amount;

    @Size(max = 100)
    @Column(name = "armada_number", length = 100)
    private String armadaNumber;

    @Size(max = 100)
    @Column(name = "container_number", length = 100)
    private String containerNumber;

    @Transient
    private boolean isPersisted;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public FormSuratJalan id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return this.status;
    }

    public FormSuratJalan status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getActive() {
        return this.active;
    }

    public FormSuratJalan active(Boolean active) {
        this.setActive(active);
        return this;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public FormSuratJalan remarks(String remarks) {
        this.setRemarks(remarks);
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getContents() {
        return this.contents;
    }

    public FormSuratJalan contents(String contents) {
        this.setContents(contents);
        return this;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getBranch() {
        return this.branch;
    }

    public FormSuratJalan branch(String branch) {
        this.setBranch(branch);
        return this;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getDocumentTitle() {
        return this.documentTitle;
    }

    public FormSuratJalan documentTitle(String documentTitle) {
        this.setDocumentTitle(documentTitle);
        return this;
    }

    public void setDocumentTitle(String documentTitle) {
        this.documentTitle = documentTitle;
    }

    public String getDocumentNumber() {
        return this.documentNumber;
    }

    public FormSuratJalan documentNumber(String documentNumber) {
        this.setDocumentNumber(documentNumber);
        return this;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getRecipientAddress() {
        return this.recipientAddress;
    }

    public FormSuratJalan recipientAddress(String recipientAddress) {
        this.setRecipientAddress(recipientAddress);
        return this;
    }

    public void setRecipientAddress(String recipientAddress) {
        this.recipientAddress = recipientAddress;
    }

    public String getNpwp() {
        return this.npwp;
    }

    public FormSuratJalan npwp(String npwp) {
        this.setNpwp(npwp);
        return this;
    }

    public void setNpwp(String npwp) {
        this.npwp = npwp;
    }

    public String getWarehouseSource() {
        return this.warehouseSource;
    }

    public FormSuratJalan warehouseSource(String warehouseSource) {
        this.setWarehouseSource(warehouseSource);
        return this;
    }

    public void setWarehouseSource(String warehouseSource) {
        this.warehouseSource = warehouseSource;
    }

    public String getDocumentSource() {
        return this.documentSource;
    }

    public FormSuratJalan documentSource(String documentSource) {
        this.setDocumentSource(documentSource);
        return this;
    }

    public void setDocumentSource(String documentSource) {
        this.documentSource = documentSource;
    }

    public String getReference() {
        return this.reference;
    }

    public FormSuratJalan reference(String reference) {
        this.setReference(reference);
        return this;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDate() {
        return this.date;
    }

    public FormSuratJalan date(String date) {
        this.setDate(date);
        return this;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getProductDescription() {
        return this.productDescription;
    }

    public FormSuratJalan productDescription(String productDescription) {
        this.setProductDescription(productDescription);
        return this;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Float getQuantity() {
        return this.quantity;
    }

    public FormSuratJalan quantity(Float quantity) {
        this.setQuantity(quantity);
        return this;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public FormSuratJalan amount(BigDecimal amount) {
        this.setAmount(amount);
        return this;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getArmadaNumber() {
        return this.armadaNumber;
    }

    public FormSuratJalan armadaNumber(String armadaNumber) {
        this.setArmadaNumber(armadaNumber);
        return this;
    }

    public void setArmadaNumber(String armadaNumber) {
        this.armadaNumber = armadaNumber;
    }

    public String getContainerNumber() {
        return this.containerNumber;
    }

    public FormSuratJalan containerNumber(String containerNumber) {
        this.setContainerNumber(containerNumber);
        return this;
    }

    public void setContainerNumber(String containerNumber) {
        this.containerNumber = containerNumber;
    }

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public FormSuratJalan setIsPersisted() {
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
        if (!(o instanceof FormSuratJalan)) {
            return false;
        }
        return id != null && id.equals(((FormSuratJalan) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FormSuratJalan{" +
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
            ", quantity=" + getQuantity() +
            ", amount=" + getAmount() +
            ", armadaNumber='" + getArmadaNumber() + "'" +
            ", containerNumber='" + getContainerNumber() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            "}";
    }
}
