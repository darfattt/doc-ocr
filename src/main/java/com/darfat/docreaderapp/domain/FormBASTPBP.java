package com.darfat.docreaderapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.domain.Persistable;

/**
 * A FormBASTPBP.
 */
@JsonIgnoreProperties(value = { "new" })
@Entity
@Table(name = "form_bastpbp")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class FormBASTPBP extends AbstractAuditingEntity implements Serializable, Persistable<String> {

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

    @Size(max = 50)
    @Column(name = "document_number", length = 50)
    private String documentNumber;

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

    @Size(max = 10)
    @Column(name = "rt_rw", length = 10)
    private String rtRw;

    @Size(max = 50)
    @Column(name = "kcu", length = 50)
    private String kcu;

    @Size(max = 50)
    @Column(name = "kantor_serah", length = 50)
    private String kantorSerah;

    @Size(max = 50)
    @Column(name = "bast_number", length = 50)
    private String bastNumber;

    @Size(max = 500)
    @Column(name = "document_description", length = 500)
    private String documentDescription;

    @Size(max = 100)
    @Column(name = "nama_1", length = 100)
    private String nama1;

    @Size(max = 100)
    @Column(name = "alamat_1", length = 100)
    private String alamat1;

    @Size(max = 50)
    @Column(name = "nomor_1", length = 50)
    private String nomor1;

    @Size(max = 3)
    @Column(name = "jumlah_1", length = 3)
    private String jumlah1;

    @Size(max = 100)
    @Column(name = "nama_2", length = 100)
    private String nama2;

    @Size(max = 100)
    @Column(name = "alamat_2", length = 100)
    private String alamat2;

    @Size(max = 50)
    @Column(name = "nomor_2", length = 50)
    private String nomor2;

    @Size(max = 3)
    @Column(name = "jumlah_2", length = 3)
    private String jumlah2;

    @Size(max = 100)
    @Column(name = "nama_3", length = 100)
    private String nama3;

    @Size(max = 100)
    @Column(name = "alamat_3", length = 100)
    private String alamat3;

    @Size(max = 50)
    @Column(name = "nomor_3", length = 50)
    private String nomor3;

    @Size(max = 3)
    @Column(name = "jumlah_3", length = 3)
    private String jumlah3;

    @Size(max = 100)
    @Column(name = "nama_4", length = 100)
    private String nama4;

    @Size(max = 100)
    @Column(name = "alamat_4", length = 100)
    private String alamat4;

    @Size(max = 50)
    @Column(name = "nomor_4", length = 50)
    private String nomor4;

    @Size(max = 3)
    @Column(name = "jumlah_4", length = 3)
    private String jumlah4;

    @Size(max = 100)
    @Column(name = "nama_5", length = 100)
    private String nama5;

    @Size(max = 100)
    @Column(name = "alamat_5", length = 100)
    private String alamat5;

    @Size(max = 50)
    @Column(name = "nomor_5", length = 50)
    private String nomor5;

    @Size(max = 3)
    @Column(name = "jumlah_5", length = 3)
    private String jumlah5;

    @Size(max = 100)
    @Column(name = "nama_6", length = 100)
    private String nama6;

    @Size(max = 100)
    @Column(name = "nama_7", length = 100)
    private String nama7;

    @Size(max = 100)
    @Column(name = "alamat_7", length = 100)
    private String alamat7;

    @Size(max = 50)
    @Column(name = "nomor_7", length = 50)
    private String nomor7;

    @Size(max = 3)
    @Column(name = "jumlah_7", length = 3)
    private String jumlah7;

    @Size(max = 100)
    @Column(name = "nama_8", length = 100)
    private String nama8;

    @Size(max = 100)
    @Column(name = "alamat_8", length = 100)
    private String alamat8;

    @Size(max = 50)
    @Column(name = "nomor_8", length = 50)
    private String nomor8;

    @Size(max = 3)
    @Column(name = "jumlah_8", length = 3)
    private String jumlah8;

    @Size(max = 100)
    @Column(name = "nama_9", length = 100)
    private String nama9;

    @Size(max = 100)
    @Column(name = "alamat_9", length = 100)
    private String alamat9;

    @Size(max = 50)
    @Column(name = "nomor_9", length = 50)
    private String nomor9;

    @Size(max = 3)
    @Column(name = "jumlah_9", length = 3)
    private String jumlah9;

    @Size(max = 100)
    @Column(name = "nama_10", length = 100)
    private String nama10;

    @Size(max = 100)
    @Column(name = "alamat_10", length = 100)
    private String alamat10;

    @Size(max = 50)
    @Column(name = "nomor_10", length = 50)
    private String nomor10;

    @Size(max = 3)
    @Column(name = "jumlah_10", length = 3)
    private String jumlah10;

    @Size(max = 100)
    @Column(name = "alamat_6", length = 100)
    private String alamat6;

    @Size(max = 50)
    @Column(name = "nomor_6", length = 50)
    private String nomor6;

    @Size(max = 3)
    @Column(name = "jumlah_6", length = 3)
    private String jumlah6;

    @Transient
    private boolean isPersisted;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public FormBASTPBP id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return this.status;
    }

    public FormBASTPBP status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getActive() {
        return this.active;
    }

    public FormBASTPBP active(Boolean active) {
        this.setActive(active);
        return this;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public FormBASTPBP remarks(String remarks) {
        this.setRemarks(remarks);
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getContents() {
        return this.contents;
    }

    public FormBASTPBP contents(String contents) {
        this.setContents(contents);
        return this;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getDocumentTitle() {
        return this.documentTitle;
    }

    public FormBASTPBP documentTitle(String documentTitle) {
        this.setDocumentTitle(documentTitle);
        return this;
    }

    public void setDocumentTitle(String documentTitle) {
        this.documentTitle = documentTitle;
    }

    public String getDocumentNumber() {
        return this.documentNumber;
    }

    public FormBASTPBP documentNumber(String documentNumber) {
        this.setDocumentNumber(documentNumber);
        return this;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getKelurahanDesa() {
        return this.kelurahanDesa;
    }

    public FormBASTPBP kelurahanDesa(String kelurahanDesa) {
        this.setKelurahanDesa(kelurahanDesa);
        return this;
    }

    public void setKelurahanDesa(String kelurahanDesa) {
        this.kelurahanDesa = kelurahanDesa;
    }

    public String getKecamatan() {
        return this.kecamatan;
    }

    public FormBASTPBP kecamatan(String kecamatan) {
        this.setKecamatan(kecamatan);
        return this;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public String getKabupatenKota() {
        return this.kabupatenKota;
    }

    public FormBASTPBP kabupatenKota(String kabupatenKota) {
        this.setKabupatenKota(kabupatenKota);
        return this;
    }

    public void setKabupatenKota(String kabupatenKota) {
        this.kabupatenKota = kabupatenKota;
    }

    public String getProvinsi() {
        return this.provinsi;
    }

    public FormBASTPBP provinsi(String provinsi) {
        this.setProvinsi(provinsi);
        return this;
    }

    public void setProvinsi(String provinsi) {
        this.provinsi = provinsi;
    }

    public String getRtRw() {
        return this.rtRw;
    }

    public FormBASTPBP rtRw(String rtRw) {
        this.setRtRw(rtRw);
        return this;
    }

    public void setRtRw(String rtRw) {
        this.rtRw = rtRw;
    }

    public String getKcu() {
        return this.kcu;
    }

    public FormBASTPBP kcu(String kcu) {
        this.setKcu(kcu);
        return this;
    }

    public void setKcu(String kcu) {
        this.kcu = kcu;
    }

    public String getKantorSerah() {
        return this.kantorSerah;
    }

    public FormBASTPBP kantorSerah(String kantorSerah) {
        this.setKantorSerah(kantorSerah);
        return this;
    }

    public void setKantorSerah(String kantorSerah) {
        this.kantorSerah = kantorSerah;
    }

    public String getBastNumber() {
        return this.bastNumber;
    }

    public FormBASTPBP bastNumber(String bastNumber) {
        this.setBastNumber(bastNumber);
        return this;
    }

    public void setBastNumber(String bastNumber) {
        this.bastNumber = bastNumber;
    }

    public String getDocumentDescription() {
        return this.documentDescription;
    }

    public FormBASTPBP documentDescription(String documentDescription) {
        this.setDocumentDescription(documentDescription);
        return this;
    }

    public void setDocumentDescription(String documentDescription) {
        this.documentDescription = documentDescription;
    }

    public String getNama1() {
        return this.nama1;
    }

    public FormBASTPBP nama1(String nama1) {
        this.setNama1(nama1);
        return this;
    }

    public void setNama1(String nama1) {
        this.nama1 = nama1;
    }

    public String getAlamat1() {
        return this.alamat1;
    }

    public FormBASTPBP alamat1(String alamat1) {
        this.setAlamat1(alamat1);
        return this;
    }

    public void setAlamat1(String alamat1) {
        this.alamat1 = alamat1;
    }

    public String getNomor1() {
        return this.nomor1;
    }

    public FormBASTPBP nomor1(String nomor1) {
        this.setNomor1(nomor1);
        return this;
    }

    public void setNomor1(String nomor1) {
        this.nomor1 = nomor1;
    }

    public String getJumlah1() {
        return this.jumlah1;
    }

    public FormBASTPBP jumlah1(String jumlah1) {
        this.setJumlah1(jumlah1);
        return this;
    }

    public void setJumlah1(String jumlah1) {
        this.jumlah1 = jumlah1;
    }

    public String getNama2() {
        return this.nama2;
    }

    public FormBASTPBP nama2(String nama2) {
        this.setNama2(nama2);
        return this;
    }

    public void setNama2(String nama2) {
        this.nama2 = nama2;
    }

    public String getAlamat2() {
        return this.alamat2;
    }

    public FormBASTPBP alamat2(String alamat2) {
        this.setAlamat2(alamat2);
        return this;
    }

    public void setAlamat2(String alamat2) {
        this.alamat2 = alamat2;
    }

    public String getNomor2() {
        return this.nomor2;
    }

    public FormBASTPBP nomor2(String nomor2) {
        this.setNomor2(nomor2);
        return this;
    }

    public void setNomor2(String nomor2) {
        this.nomor2 = nomor2;
    }

    public String getJumlah2() {
        return this.jumlah2;
    }

    public FormBASTPBP jumlah2(String jumlah2) {
        this.setJumlah2(jumlah2);
        return this;
    }

    public void setJumlah2(String jumlah2) {
        this.jumlah2 = jumlah2;
    }

    public String getNama3() {
        return this.nama3;
    }

    public FormBASTPBP nama3(String nama3) {
        this.setNama3(nama3);
        return this;
    }

    public void setNama3(String nama3) {
        this.nama3 = nama3;
    }

    public String getAlamat3() {
        return this.alamat3;
    }

    public FormBASTPBP alamat3(String alamat3) {
        this.setAlamat3(alamat3);
        return this;
    }

    public void setAlamat3(String alamat3) {
        this.alamat3 = alamat3;
    }

    public String getNomor3() {
        return this.nomor3;
    }

    public FormBASTPBP nomor3(String nomor3) {
        this.setNomor3(nomor3);
        return this;
    }

    public void setNomor3(String nomor3) {
        this.nomor3 = nomor3;
    }

    public String getJumlah3() {
        return this.jumlah3;
    }

    public FormBASTPBP jumlah3(String jumlah3) {
        this.setJumlah3(jumlah3);
        return this;
    }

    public void setJumlah3(String jumlah3) {
        this.jumlah3 = jumlah3;
    }

    public String getNama4() {
        return this.nama4;
    }

    public FormBASTPBP nama4(String nama4) {
        this.setNama4(nama4);
        return this;
    }

    public void setNama4(String nama4) {
        this.nama4 = nama4;
    }

    public String getAlamat4() {
        return this.alamat4;
    }

    public FormBASTPBP alamat4(String alamat4) {
        this.setAlamat4(alamat4);
        return this;
    }

    public void setAlamat4(String alamat4) {
        this.alamat4 = alamat4;
    }

    public String getNomor4() {
        return this.nomor4;
    }

    public FormBASTPBP nomor4(String nomor4) {
        this.setNomor4(nomor4);
        return this;
    }

    public void setNomor4(String nomor4) {
        this.nomor4 = nomor4;
    }

    public String getJumlah4() {
        return this.jumlah4;
    }

    public FormBASTPBP jumlah4(String jumlah4) {
        this.setJumlah4(jumlah4);
        return this;
    }

    public void setJumlah4(String jumlah4) {
        this.jumlah4 = jumlah4;
    }

    public String getNama5() {
        return this.nama5;
    }

    public FormBASTPBP nama5(String nama5) {
        this.setNama5(nama5);
        return this;
    }

    public void setNama5(String nama5) {
        this.nama5 = nama5;
    }

    public String getAlamat5() {
        return this.alamat5;
    }

    public FormBASTPBP alamat5(String alamat5) {
        this.setAlamat5(alamat5);
        return this;
    }

    public void setAlamat5(String alamat5) {
        this.alamat5 = alamat5;
    }

    public String getNomor5() {
        return this.nomor5;
    }

    public FormBASTPBP nomor5(String nomor5) {
        this.setNomor5(nomor5);
        return this;
    }

    public void setNomor5(String nomor5) {
        this.nomor5 = nomor5;
    }

    public String getJumlah5() {
        return this.jumlah5;
    }

    public FormBASTPBP jumlah5(String jumlah5) {
        this.setJumlah5(jumlah5);
        return this;
    }

    public void setJumlah5(String jumlah5) {
        this.jumlah5 = jumlah5;
    }

    public String getNama6() {
        return this.nama6;
    }

    public FormBASTPBP nama6(String nama6) {
        this.setNama6(nama6);
        return this;
    }

    public void setNama6(String nama6) {
        this.nama6 = nama6;
    }

    public String getNama7() {
        return this.nama7;
    }

    public FormBASTPBP nama7(String nama7) {
        this.setNama7(nama7);
        return this;
    }

    public void setNama7(String nama7) {
        this.nama7 = nama7;
    }

    public String getAlamat7() {
        return this.alamat7;
    }

    public FormBASTPBP alamat7(String alamat7) {
        this.setAlamat7(alamat7);
        return this;
    }

    public void setAlamat7(String alamat7) {
        this.alamat7 = alamat7;
    }

    public String getNomor7() {
        return this.nomor7;
    }

    public FormBASTPBP nomor7(String nomor7) {
        this.setNomor7(nomor7);
        return this;
    }

    public void setNomor7(String nomor7) {
        this.nomor7 = nomor7;
    }

    public String getJumlah7() {
        return this.jumlah7;
    }

    public FormBASTPBP jumlah7(String jumlah7) {
        this.setJumlah7(jumlah7);
        return this;
    }

    public void setJumlah7(String jumlah7) {
        this.jumlah7 = jumlah7;
    }

    public String getNama8() {
        return this.nama8;
    }

    public FormBASTPBP nama8(String nama8) {
        this.setNama8(nama8);
        return this;
    }

    public void setNama8(String nama8) {
        this.nama8 = nama8;
    }

    public String getAlamat8() {
        return this.alamat8;
    }

    public FormBASTPBP alamat8(String alamat8) {
        this.setAlamat8(alamat8);
        return this;
    }

    public void setAlamat8(String alamat8) {
        this.alamat8 = alamat8;
    }

    public String getNomor8() {
        return this.nomor8;
    }

    public FormBASTPBP nomor8(String nomor8) {
        this.setNomor8(nomor8);
        return this;
    }

    public void setNomor8(String nomor8) {
        this.nomor8 = nomor8;
    }

    public String getJumlah8() {
        return this.jumlah8;
    }

    public FormBASTPBP jumlah8(String jumlah8) {
        this.setJumlah8(jumlah8);
        return this;
    }

    public void setJumlah8(String jumlah8) {
        this.jumlah8 = jumlah8;
    }

    public String getNama9() {
        return this.nama9;
    }

    public FormBASTPBP nama9(String nama9) {
        this.setNama9(nama9);
        return this;
    }

    public void setNama9(String nama9) {
        this.nama9 = nama9;
    }

    public String getAlamat9() {
        return this.alamat9;
    }

    public FormBASTPBP alamat9(String alamat9) {
        this.setAlamat9(alamat9);
        return this;
    }

    public void setAlamat9(String alamat9) {
        this.alamat9 = alamat9;
    }

    public String getNomor9() {
        return this.nomor9;
    }

    public FormBASTPBP nomor9(String nomor9) {
        this.setNomor9(nomor9);
        return this;
    }

    public void setNomor9(String nomor9) {
        this.nomor9 = nomor9;
    }

    public String getJumlah9() {
        return this.jumlah9;
    }

    public FormBASTPBP jumlah9(String jumlah9) {
        this.setJumlah9(jumlah9);
        return this;
    }

    public void setJumlah9(String jumlah9) {
        this.jumlah9 = jumlah9;
    }

    public String getNama10() {
        return this.nama10;
    }

    public FormBASTPBP nama10(String nama10) {
        this.setNama10(nama10);
        return this;
    }

    public void setNama10(String nama10) {
        this.nama10 = nama10;
    }

    public String getAlamat10() {
        return this.alamat10;
    }

    public FormBASTPBP alamat10(String alamat10) {
        this.setAlamat10(alamat10);
        return this;
    }

    public void setAlamat10(String alamat10) {
        this.alamat10 = alamat10;
    }

    public String getNomor10() {
        return this.nomor10;
    }

    public FormBASTPBP nomor10(String nomor10) {
        this.setNomor10(nomor10);
        return this;
    }

    public void setNomor10(String nomor10) {
        this.nomor10 = nomor10;
    }

    public String getJumlah10() {
        return this.jumlah10;
    }

    public FormBASTPBP jumlah10(String jumlah10) {
        this.setJumlah10(jumlah10);
        return this;
    }

    public void setJumlah10(String jumlah10) {
        this.jumlah10 = jumlah10;
    }

    public String getAlamat6() {
        return this.alamat6;
    }

    public FormBASTPBP alamat6(String alamat6) {
        this.setAlamat6(alamat6);
        return this;
    }

    public void setAlamat6(String alamat6) {
        this.alamat6 = alamat6;
    }

    public String getNomor6() {
        return this.nomor6;
    }

    public FormBASTPBP nomor6(String nomor6) {
        this.setNomor6(nomor6);
        return this;
    }

    public void setNomor6(String nomor6) {
        this.nomor6 = nomor6;
    }

    public String getJumlah6() {
        return this.jumlah6;
    }

    public FormBASTPBP jumlah6(String jumlah6) {
        this.setJumlah6(jumlah6);
        return this;
    }

    public void setJumlah6(String jumlah6) {
        this.jumlah6 = jumlah6;
    }

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public FormBASTPBP setIsPersisted() {
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
        if (!(o instanceof FormBASTPBP)) {
            return false;
        }
        return id != null && id.equals(((FormBASTPBP) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FormBASTPBP{" +
            "id=" + getId() +
            ", status='" + getStatus() + "'" +
            ", active='" + getActive() + "'" +
            ", remarks='" + getRemarks() + "'" +
            ", contents='" + getContents() + "'" +
            ", documentTitle='" + getDocumentTitle() + "'" +
            ", documentNumber='" + getDocumentNumber() + "'" +
            ", kelurahanDesa='" + getKelurahanDesa() + "'" +
            ", kecamatan='" + getKecamatan() + "'" +
            ", kabupatenKota='" + getKabupatenKota() + "'" +
            ", provinsi='" + getProvinsi() + "'" +
            ", rtRw='" + getRtRw() + "'" +
            ", kcu='" + getKcu() + "'" +
            ", kantorSerah='" + getKantorSerah() + "'" +
            ", bastNumber='" + getBastNumber() + "'" +
            ", documentDescription='" + getDocumentDescription() + "'" +
            ", nama1='" + getNama1() + "'" +
            ", alamat1='" + getAlamat1() + "'" +
            ", nomor1='" + getNomor1() + "'" +
            ", jumlah1='" + getJumlah1() + "'" +
            ", nama2='" + getNama2() + "'" +
            ", alamat2='" + getAlamat2() + "'" +
            ", nomor2='" + getNomor2() + "'" +
            ", jumlah2='" + getJumlah2() + "'" +
            ", nama3='" + getNama3() + "'" +
            ", alamat3='" + getAlamat3() + "'" +
            ", nomor3='" + getNomor3() + "'" +
            ", jumlah3='" + getJumlah3() + "'" +
            ", nama4='" + getNama4() + "'" +
            ", alamat4='" + getAlamat4() + "'" +
            ", nomor4='" + getNomor4() + "'" +
            ", jumlah4='" + getJumlah4() + "'" +
            ", nama5='" + getNama5() + "'" +
            ", alamat5='" + getAlamat5() + "'" +
            ", nomor5='" + getNomor5() + "'" +
            ", jumlah5='" + getJumlah5() + "'" +
            ", nama6='" + getNama6() + "'" +
            ", nama7='" + getNama7() + "'" +
            ", alamat7='" + getAlamat7() + "'" +
            ", nomor7='" + getNomor7() + "'" +
            ", jumlah7='" + getJumlah7() + "'" +
            ", nama8='" + getNama8() + "'" +
            ", alamat8='" + getAlamat8() + "'" +
            ", nomor8='" + getNomor8() + "'" +
            ", jumlah8='" + getJumlah8() + "'" +
            ", nama9='" + getNama9() + "'" +
            ", alamat9='" + getAlamat9() + "'" +
            ", nomor9='" + getNomor9() + "'" +
            ", jumlah9='" + getJumlah9() + "'" +
            ", nama10='" + getNama10() + "'" +
            ", alamat10='" + getAlamat10() + "'" +
            ", nomor10='" + getNomor10() + "'" +
            ", jumlah10='" + getJumlah10() + "'" +
            ", alamat6='" + getAlamat6() + "'" +
            ", nomor6='" + getNomor6() + "'" +
            ", jumlah6='" + getJumlah6() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            "}";
    }
}
