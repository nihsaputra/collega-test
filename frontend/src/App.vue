<template>
  <div class="container mt-3">
    <div class="row">
      <div class="col-5">
        <h2>Form Pelanggan</h2>
        <form @submit.prevent="onHandleSubmit">
          <div class="mb-3">
            <label for="nama" class="form-label">Nama</label>
            <input type="text" class="form-control" v-model="inputNama" />
          </div>
          <div class="mb-3">
            <label for="alamat" class="form-label">Alamat</label>
            <input type="text" class="form-control" v-model="inputAlamat" />
          </div>
          <div class="mb-3">
            <div class="mb-3">Jenis Kelamin</div>
            <div class="form-check">
              <input
                type="radio"
                class="form-check-input"
                id="laki-laki"
                value="laki-laki"
                v-model="inputJenisKelamin"
              />
              <label for="laki-laki" class="form-check-label">Laki-Laki</label>
            </div>
            <div class="form-check">
              <input
                type="radio"
                class="form-check-input"
                id="perempuan"
                value="perempuan"
                v-model="inputJenisKelamin"
              />
              <label for="perempuan" class="form-check-label">Perempuan</label>
            </div>
          </div>
          <div class="mb-3">
            <label for="umur" class="form-label">Umur</label>
            <input type="text" class="form-control" v-model="inputUmur" />
            <label for="umur" class="text-danger" v-if="umurError">umur tidak boleh minus</label>
          </div>
          <div class="mb-3">
            <label for="pekerjaan" class="form-label">Pekerjaan</label>
            <input type="text" class="form-control" v-model="inputPekerjaan" />
          </div>
          <div class="mb-3">
            <label for="penghasilan" class="form-label">Penghasilan</label>
            <input
              type="text"
              class="form-control"
              v-model="inputPenghasilan"
            />
            <label for="umur" class="text-danger" v-if="penghasilanError">penghasilan tidak boleh minus</label>
          </div>
          <div class="mb-3">
            <label for="penghasilan" class="form-label">Hobby</label>
            <input type="text" class="form-control" v-model="inputHobby" />
          </div>
          <div class="mb-3">
            <button
              type="submit"
              class="btn me-2"
              :class="isUpdate ? 'btn-info' : 'btn-primary'"
            >
              {{ isUpdate ? "Update" : "Save" }}
            </button>
            <button
              v-if="isUpdate"
              type="button"
              class="btn btn-secondary"
              @click="clearInput"
            >
              Cancel
            </button>
          </div>
        </form>
      </div>
      <div class="col-1"></div>
      <div class="col-6">
        <h2>Daftar Pelanggan</h2>
        <table class="table table-sm">
          <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">Nama</th>
              <th scope="col">Alamat</th>
              <th scope="col">Umur</th>
              <th scope="col">Pekerjaan</th>
              <th scope="col">Penghasilan</th>
              <th scope="col">Action</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(pelanggan, index) in dataPelanggan">
              <th scope="row">{{ index + 1 }}</th>
              <td>{{ pelanggan.nama }}</td>
              <td>{{ pelanggan.alamat }}</td>
              <td>{{ pelanggan.umur }}</td>
              <td>{{ pelanggan.pekerjaan }}</td>
              <td>{{ pelanggan.penghasilan }}</td>
              <td>
                <input
                  type="button"
                  class="btn btn-info btn-sm me-2"
                  value="Edit"
                  @click="getDataProduct(pelanggan)"
                />
                <input
                  type="button"
                  class="btn btn-danger btn-sm me-2"
                  value="Delete"
                  @click="deleteProduct(pelanggan.id)"
                />
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script setup>
import { onMounted, ref, watch } from "vue";
import axios from "axios";

const url = "http://localhost:8080/api/v1";
const dataPelanggan = ref([]);
const isUpdate = ref(false);

const inputNama = ref("");
const inputAlamat = ref("");
const inputJenisKelamin = ref("");
const inputUmur = ref("");
const inputPekerjaan = ref("");
const inputPenghasilan = ref("");
const inputHobby = ref("");
const idPelanggan = ref("");

const getPelanggan = async () => {
  try {
    const response = await axios.get(url + "/pelanggan");
    dataPelanggan.value = response.data.data;
    console.log(dataPelanggan);
  } catch (error) {
    console.error(error);
  }
};

const clearInput = () => {
  idPelanggan.value = "";
  inputNama.value = "";
  inputAlamat.value = "";
  inputJenisKelamin.value = "";
  inputUmur.value = "";
  inputPekerjaan.value = "";
  inputPenghasilan.value = "";
  inputHobby.value = "";
  isUpdate.value = false;
};

const getDataProduct = async (objPelanggan) => {
  idPelanggan.value = objPelanggan.id;
  inputNama.value = objPelanggan.nama;
  inputAlamat.value = objPelanggan.alamat;
  inputJenisKelamin.value = objPelanggan.jenis_kelamin;
  inputUmur.value = objPelanggan.umur;
  inputPekerjaan.value = objPelanggan.pekerjaan;
  inputPenghasilan.value = objPelanggan.penghasilan;
  inputHobby.value = objPelanggan.hobby;
  isUpdate.value = true;
};

const onHandleSubmit = async () => {
  validasiError();
  if (isUpdate.value == false) {
    await axios.post(url + "/pelanggan", {
      nama: inputNama.value,
      alamat: inputAlamat.value,
      jenis_kelamin: inputJenisKelamin.value,
      umur: inputUmur.value,
      pekerjaan: inputPekerjaan.value,
      penghasilan: inputPenghasilan.value,
      hobby: inputHobby.value,
    });
    alert("create new pelanggan successfully");
  } else {
    await axios.put(url + "/pelanggan", {
      id: idPelanggan.value,
      nama: inputNama.value,
      alamat: inputAlamat.value,
      jenis_kelamin: inputJenisKelamin.value,
      umur: inputUmur.value,
      pekerjaan: inputPekerjaan.value,
      penghasilan: inputPenghasilan.value,
      hobby: inputHobby.value,
    });
    alert("update pelanggan successfully");
  }
  clearInput();
  getPelanggan();
};

const deleteProduct = async (id) => {
  await axios.delete(url + "/pelanggan/" + id);
  alert("delete pelanggan successfully");
  getPelanggan();
};

//validasi
const umurError = ref(false);
const penghasilanError = ref(false);
const validasiError = ()=>{
  if(inputUmur.value < 0 ) umurError.value = true;
  if(inputPenghasilan.value < 0 ) penghasilanError.value = true;
}

onMounted(() => {
  getPelanggan();
});
</script>
