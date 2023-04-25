package com.darfat.docreaderapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.domain.Persistable;

/**
 * A FormPernyataan.
 */
@JsonIgnoreProperties(value = { "new" })
@Entity
@Table(name = "form_pernyataan")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class FormPernyataan extends AbstractAuditingEntity implements Serializable, Persistable<String> {

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

    @Size(max = 500)
    @Column(name = "document_title", length = 500)
    private String documentTitle;

    @Size(max = 100)
    @Column(name = "officer_name", length = 100)
    private String officerName;

    @Size(max = 50)
    @Column(name = "officer_phone_number", length = 50)
    private String officerPhoneNumber;

    @Size(max = 100)
    @Column(name = "officer_position", length = 100)
    private String officerPosition;

    @Size(max = 100)
    @Column(name = "officer_department", length = 100)
    private String officerDepartment;

    @Size(max = 100)
    @Column(name = "kelurahan_desa", length = 100)
    private String kelurahanDesa;

    @Size(max = 100)
    @Column(name = "kecamatan", length = 100)
    private String kecamatan;

    @Size(max = 100)
    @Column(name = "kabupaten_kota", length = 100)
    private String kabupatenKota;

    @Size(max = 100)
    @Column(name = "provinsi", length = 100)
    private String provinsi;

    @Size(max = 100)
    @Column(name = "pbp_tidak_ditemukan_1", length = 100)
    private String pbpTidakDitemukan1;

    @Size(max = 100)
    @Column(name = "alamat_pbp_tidak_ditemukan_1", length = 100)
    private String alamatPbpTidakDitemukan1;

    @Size(max = 100)
    @Column(name = "pbp_pengganti_1", length = 100)
    private String pbpPengganti1;

    @Size(max = 100)
    @Column(name = "alamat_pbp_pengganti_1", length = 100)
    private String alamatPbpPengganti1;

    @Size(max = 100)
    @Column(name = "pbp_tidak_ditemukan_2", length = 100)
    private String pbpTidakDitemukan2;

    @Size(max = 100)
    @Column(name = "alamat_pbp_tidak_ditemukan_2", length = 100)
    private String alamatPbpTidakDitemukan2;

    @Size(max = 100)
    @Column(name = "pbp_pengganti_2", length = 100)
    private String pbpPengganti2;

    @Size(max = 100)
    @Column(name = "alamat_pbp_pengganti_2", length = 100)
    private String alamatPbpPengganti2;

    @Size(max = 100)
    @Column(name = "pbp_tidak_ditemukan_3", length = 100)
    private String pbpTidakDitemukan3;

    @Size(max = 100)
    @Column(name = "alamat_pbp_tidak_ditemukan_3", length = 100)
    private String alamatPbpTidakDitemukan3;

    @Size(max = 100)
    @Column(name = "pbp_pengganti_3", length = 100)
    private String pbpPengganti3;

    @Size(max = 100)
    @Column(name = "alamat_pbp_pengganti_3", length = 100)
    private String alamatPbpPengganti3;

    @Size(max = 100)
    @Column(name = "pbp_tidak_ditemukan_4", length = 100)
    private String pbpTidakDitemukan4;

    @Size(max = 100)
    @Column(name = "alamat_pbp_tidak_ditemukan_4", length = 100)
    private String alamatPbpTidakDitemukan4;

    @Size(max = 100)
    @Column(name = "pbp_pengganti_4", length = 100)
    private String pbpPengganti4;

    @Size(max = 100)
    @Column(name = "alamat_pbp_pengganti_4", length = 100)
    private String alamatPbpPengganti4;

    @Size(max = 100)
    @Column(name = "pbp_tidak_ditemukan_5", length = 100)
    private String pbpTidakDitemukan5;

    @Size(max = 100)
    @Column(name = "alamat_pbp_tidak_ditemukan_5", length = 100)
    private String alamatPbpTidakDitemukan5;

    @Size(max = 100)
    @Column(name = "pbp_pengganti_5", length = 100)
    private String pbpPengganti5;

    @Size(max = 100)
    @Column(name = "alamat_pbp_pengganti_5", length = 100)
    private String alamatPbpPengganti5;

    @Transient
    private boolean isPersisted;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public FormPernyataan id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return this.status;
    }

    public FormPernyataan status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getActive() {
        return this.active;
    }

    public FormPernyataan active(Boolean active) {
        this.setActive(active);
        return this;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public FormPernyataan remarks(String remarks) {
        this.setRemarks(remarks);
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getContents() {
        return this.contents;
    }

    public FormPernyataan contents(String contents) {
        this.setContents(contents);
        return this;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getDocumentTitle() {
        return this.documentTitle;
    }

    public FormPernyataan documentTitle(String documentTitle) {
        this.setDocumentTitle(documentTitle);
        return this;
    }

    public void setDocumentTitle(String documentTitle) {
        this.documentTitle = documentTitle;
    }

    public String getOfficerName() {
        return this.officerName;
    }

    public FormPernyataan officerName(String officerName) {
        this.setOfficerName(officerName);
        return this;
    }

    public void setOfficerName(String officerName) {
        this.officerName = officerName;
    }

    public String getOfficerPhoneNumber() {
        return this.officerPhoneNumber;
    }

    public FormPernyataan officerPhoneNumber(String officerPhoneNumber) {
        this.setOfficerPhoneNumber(officerPhoneNumber);
        return this;
    }

    public void setOfficerPhoneNumber(String officerPhoneNumber) {
        this.officerPhoneNumber = officerPhoneNumber;
    }

    public String getOfficerPosition() {
        return this.officerPosition;
    }

    public FormPernyataan officerPosition(String officerPosition) {
        this.setOfficerPosition(officerPosition);
        return this;
    }

    public void setOfficerPosition(String officerPosition) {
        this.officerPosition = officerPosition;
    }

    public String getOfficerDepartment() {
        return this.officerDepartment;
    }

    public FormPernyataan officerDepartment(String officerDepartment) {
        this.setOfficerDepartment(officerDepartment);
        return this;
    }

    public void setOfficerDepartment(String officerDepartment) {
        this.officerDepartment = officerDepartment;
    }

    public String getKelurahanDesa() {
        return this.kelurahanDesa;
    }

    public FormPernyataan kelurahanDesa(String kelurahanDesa) {
        this.setKelurahanDesa(kelurahanDesa);
        return this;
    }

    public void setKelurahanDesa(String kelurahanDesa) {
        this.kelurahanDesa = kelurahanDesa;
    }

    public String getKecamatan() {
        return this.kecamatan;
    }

    public FormPernyataan kecamatan(String kecamatan) {
        this.setKecamatan(kecamatan);
        return this;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public String getKabupatenKota() {
        return this.kabupatenKota;
    }

    public FormPernyataan kabupatenKota(String kabupatenKota) {
        this.setKabupatenKota(kabupatenKota);
        return this;
    }

    public void setKabupatenKota(String kabupatenKota) {
        this.kabupatenKota = kabupatenKota;
    }

    public String getProvinsi() {
        return this.provinsi;
    }

    public FormPernyataan provinsi(String provinsi) {
        this.setProvinsi(provinsi);
        return this;
    }

    public void setProvinsi(String provinsi) {
        this.provinsi = provinsi;
    }

    public String getPbpTidakDitemukan1() {
        return this.pbpTidakDitemukan1;
    }

    public FormPernyataan pbpTidakDitemukan1(String pbpTidakDitemukan1) {
        this.setPbpTidakDitemukan1(pbpTidakDitemukan1);
        return this;
    }

    public void setPbpTidakDitemukan1(String pbpTidakDitemukan1) {
        this.pbpTidakDitemukan1 = pbpTidakDitemukan1;
    }

    public String getAlamatPbpTidakDitemukan1() {
        return this.alamatPbpTidakDitemukan1;
    }

    public FormPernyataan alamatPbpTidakDitemukan1(String alamatPbpTidakDitemukan1) {
        this.setAlamatPbpTidakDitemukan1(alamatPbpTidakDitemukan1);
        return this;
    }

    public void setAlamatPbpTidakDitemukan1(String alamatPbpTidakDitemukan1) {
        this.alamatPbpTidakDitemukan1 = alamatPbpTidakDitemukan1;
    }

    public String getPbpPengganti1() {
        return this.pbpPengganti1;
    }

    public FormPernyataan pbpPengganti1(String pbpPengganti1) {
        this.setPbpPengganti1(pbpPengganti1);
        return this;
    }

    public void setPbpPengganti1(String pbpPengganti1) {
        this.pbpPengganti1 = pbpPengganti1;
    }

    public String getAlamatPbpPengganti1() {
        return this.alamatPbpPengganti1;
    }

    public FormPernyataan alamatPbpPengganti1(String alamatPbpPengganti1) {
        this.setAlamatPbpPengganti1(alamatPbpPengganti1);
        return this;
    }

    public void setAlamatPbpPengganti1(String alamatPbpPengganti1) {
        this.alamatPbpPengganti1 = alamatPbpPengganti1;
    }

    public String getPbpTidakDitemukan2() {
        return this.pbpTidakDitemukan2;
    }

    public FormPernyataan pbpTidakDitemukan2(String pbpTidakDitemukan2) {
        this.setPbpTidakDitemukan2(pbpTidakDitemukan2);
        return this;
    }

    public void setPbpTidakDitemukan2(String pbpTidakDitemukan2) {
        this.pbpTidakDitemukan2 = pbpTidakDitemukan2;
    }

    public String getAlamatPbpTidakDitemukan2() {
        return this.alamatPbpTidakDitemukan2;
    }

    public FormPernyataan alamatPbpTidakDitemukan2(String alamatPbpTidakDitemukan2) {
        this.setAlamatPbpTidakDitemukan2(alamatPbpTidakDitemukan2);
        return this;
    }

    public void setAlamatPbpTidakDitemukan2(String alamatPbpTidakDitemukan2) {
        this.alamatPbpTidakDitemukan2 = alamatPbpTidakDitemukan2;
    }

    public String getPbpPengganti2() {
        return this.pbpPengganti2;
    }

    public FormPernyataan pbpPengganti2(String pbpPengganti2) {
        this.setPbpPengganti2(pbpPengganti2);
        return this;
    }

    public void setPbpPengganti2(String pbpPengganti2) {
        this.pbpPengganti2 = pbpPengganti2;
    }

    public String getAlamatPbpPengganti2() {
        return this.alamatPbpPengganti2;
    }

    public FormPernyataan alamatPbpPengganti2(String alamatPbpPengganti2) {
        this.setAlamatPbpPengganti2(alamatPbpPengganti2);
        return this;
    }

    public void setAlamatPbpPengganti2(String alamatPbpPengganti2) {
        this.alamatPbpPengganti2 = alamatPbpPengganti2;
    }

    public String getPbpTidakDitemukan3() {
        return this.pbpTidakDitemukan3;
    }

    public FormPernyataan pbpTidakDitemukan3(String pbpTidakDitemukan3) {
        this.setPbpTidakDitemukan3(pbpTidakDitemukan3);
        return this;
    }

    public void setPbpTidakDitemukan3(String pbpTidakDitemukan3) {
        this.pbpTidakDitemukan3 = pbpTidakDitemukan3;
    }

    public String getAlamatPbpTidakDitemukan3() {
        return this.alamatPbpTidakDitemukan3;
    }

    public FormPernyataan alamatPbpTidakDitemukan3(String alamatPbpTidakDitemukan3) {
        this.setAlamatPbpTidakDitemukan3(alamatPbpTidakDitemukan3);
        return this;
    }

    public void setAlamatPbpTidakDitemukan3(String alamatPbpTidakDitemukan3) {
        this.alamatPbpTidakDitemukan3 = alamatPbpTidakDitemukan3;
    }

    public String getPbpPengganti3() {
        return this.pbpPengganti3;
    }

    public FormPernyataan pbpPengganti3(String pbpPengganti3) {
        this.setPbpPengganti3(pbpPengganti3);
        return this;
    }

    public void setPbpPengganti3(String pbpPengganti3) {
        this.pbpPengganti3 = pbpPengganti3;
    }

    public String getAlamatPbpPengganti3() {
        return this.alamatPbpPengganti3;
    }

    public FormPernyataan alamatPbpPengganti3(String alamatPbpPengganti3) {
        this.setAlamatPbpPengganti3(alamatPbpPengganti3);
        return this;
    }

    public void setAlamatPbpPengganti3(String alamatPbpPengganti3) {
        this.alamatPbpPengganti3 = alamatPbpPengganti3;
    }

    public String getPbpTidakDitemukan4() {
        return this.pbpTidakDitemukan4;
    }

    public FormPernyataan pbpTidakDitemukan4(String pbpTidakDitemukan4) {
        this.setPbpTidakDitemukan4(pbpTidakDitemukan4);
        return this;
    }

    public void setPbpTidakDitemukan4(String pbpTidakDitemukan4) {
        this.pbpTidakDitemukan4 = pbpTidakDitemukan4;
    }

    public String getAlamatPbpTidakDitemukan4() {
        return this.alamatPbpTidakDitemukan4;
    }

    public FormPernyataan alamatPbpTidakDitemukan4(String alamatPbpTidakDitemukan4) {
        this.setAlamatPbpTidakDitemukan4(alamatPbpTidakDitemukan4);
        return this;
    }

    public void setAlamatPbpTidakDitemukan4(String alamatPbpTidakDitemukan4) {
        this.alamatPbpTidakDitemukan4 = alamatPbpTidakDitemukan4;
    }

    public String getPbpPengganti4() {
        return this.pbpPengganti4;
    }

    public FormPernyataan pbpPengganti4(String pbpPengganti4) {
        this.setPbpPengganti4(pbpPengganti4);
        return this;
    }

    public void setPbpPengganti4(String pbpPengganti4) {
        this.pbpPengganti4 = pbpPengganti4;
    }

    public String getAlamatPbpPengganti4() {
        return this.alamatPbpPengganti4;
    }

    public FormPernyataan alamatPbpPengganti4(String alamatPbpPengganti4) {
        this.setAlamatPbpPengganti4(alamatPbpPengganti4);
        return this;
    }

    public void setAlamatPbpPengganti4(String alamatPbpPengganti4) {
        this.alamatPbpPengganti4 = alamatPbpPengganti4;
    }

    public String getPbpTidakDitemukan5() {
        return this.pbpTidakDitemukan5;
    }

    public FormPernyataan pbpTidakDitemukan5(String pbpTidakDitemukan5) {
        this.setPbpTidakDitemukan5(pbpTidakDitemukan5);
        return this;
    }

    public void setPbpTidakDitemukan5(String pbpTidakDitemukan5) {
        this.pbpTidakDitemukan5 = pbpTidakDitemukan5;
    }

    public String getAlamatPbpTidakDitemukan5() {
        return this.alamatPbpTidakDitemukan5;
    }

    public FormPernyataan alamatPbpTidakDitemukan5(String alamatPbpTidakDitemukan5) {
        this.setAlamatPbpTidakDitemukan5(alamatPbpTidakDitemukan5);
        return this;
    }

    public void setAlamatPbpTidakDitemukan5(String alamatPbpTidakDitemukan5) {
        this.alamatPbpTidakDitemukan5 = alamatPbpTidakDitemukan5;
    }

    public String getPbpPengganti5() {
        return this.pbpPengganti5;
    }

    public FormPernyataan pbpPengganti5(String pbpPengganti5) {
        this.setPbpPengganti5(pbpPengganti5);
        return this;
    }

    public void setPbpPengganti5(String pbpPengganti5) {
        this.pbpPengganti5 = pbpPengganti5;
    }

    public String getAlamatPbpPengganti5() {
        return this.alamatPbpPengganti5;
    }

    public FormPernyataan alamatPbpPengganti5(String alamatPbpPengganti5) {
        this.setAlamatPbpPengganti5(alamatPbpPengganti5);
        return this;
    }

    public void setAlamatPbpPengganti5(String alamatPbpPengganti5) {
        this.alamatPbpPengganti5 = alamatPbpPengganti5;
    }

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public FormPernyataan setIsPersisted() {
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
        if (!(o instanceof FormPernyataan)) {
            return false;
        }
        return id != null && id.equals(((FormPernyataan) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FormPernyataan{" +
            "id=" + getId() +
            ", status='" + getStatus() + "'" +
            ", active='" + getActive() + "'" +
            ", remarks='" + getRemarks() + "'" +
            ", contents='" + getContents() + "'" +
            ", documentTitle='" + getDocumentTitle() + "'" +
            ", officerName='" + getOfficerName() + "'" +
            ", officerPhoneNumber='" + getOfficerPhoneNumber() + "'" +
            ", officerPosition='" + getOfficerPosition() + "'" +
            ", officerDepartment='" + getOfficerDepartment() + "'" +
            ", kelurahanDesa='" + getKelurahanDesa() + "'" +
            ", kecamatan='" + getKecamatan() + "'" +
            ", kabupatenKota='" + getKabupatenKota() + "'" +
            ", provinsi='" + getProvinsi() + "'" +
            ", pbpTidakDitemukan1='" + getPbpTidakDitemukan1() + "'" +
            ", alamatPbpTidakDitemukan1='" + getAlamatPbpTidakDitemukan1() + "'" +
            ", pbpPengganti1='" + getPbpPengganti1() + "'" +
            ", alamatPbpPengganti1='" + getAlamatPbpPengganti1() + "'" +
            ", pbpTidakDitemukan2='" + getPbpTidakDitemukan2() + "'" +
            ", alamatPbpTidakDitemukan2='" + getAlamatPbpTidakDitemukan2() + "'" +
            ", pbpPengganti2='" + getPbpPengganti2() + "'" +
            ", alamatPbpPengganti2='" + getAlamatPbpPengganti2() + "'" +
            ", pbpTidakDitemukan3='" + getPbpTidakDitemukan3() + "'" +
            ", alamatPbpTidakDitemukan3='" + getAlamatPbpTidakDitemukan3() + "'" +
            ", pbpPengganti3='" + getPbpPengganti3() + "'" +
            ", alamatPbpPengganti3='" + getAlamatPbpPengganti3() + "'" +
            ", pbpTidakDitemukan4='" + getPbpTidakDitemukan4() + "'" +
            ", alamatPbpTidakDitemukan4='" + getAlamatPbpTidakDitemukan4() + "'" +
            ", pbpPengganti4='" + getPbpPengganti4() + "'" +
            ", alamatPbpPengganti4='" + getAlamatPbpPengganti4() + "'" +
            ", pbpTidakDitemukan5='" + getPbpTidakDitemukan5() + "'" +
            ", alamatPbpTidakDitemukan5='" + getAlamatPbpTidakDitemukan5() + "'" +
            ", pbpPengganti5='" + getPbpPengganti5() + "'" +
            ", alamatPbpPengganti5='" + getAlamatPbpPengganti5() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            "}";
    }
}
