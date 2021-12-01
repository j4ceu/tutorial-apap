# Tutorial APAP
## Authors
Sutan Raihan Maulaya - 1906305820 - C

## Tutorial 7
### What I have learned today
Pada Tutorial 6 saya belajar tentang bagaimana menggunakan React untuk frontend. Pada React tersebut saya juga belajar bagaimana membuat react class-based dan function-based.

### Pertanyaan
1. Jelaskan apa yang Anda lakukan di latihan dalam satu paragraf per-soal. Berikan screenshot
sebagai ilustrasi dari apa yang Anda jelaskan.\
**Jawab**\
**Latihan 1**\
Pada latihan 1 saya membuat suatu fungsi baru untuk onClick pada icon Delete yang bernama handleDeleteItemFromCart. Fungsi tersebut akan menghapus item yang sudah masuk ke dalam cart.
Gambar dibawah merupakan fungsi yang saya buat untuk function-based\
![image](https://user-images.githubusercontent.com/71779362/143258474-1ea2c2a3-23bb-418f-a5df-441604cdbcfb.png)\
Gambar dibawah merupakan fungsi yang saya buat untuk class-based\
![image](https://user-images.githubusercontent.com/71779362/143258732-4e4d1038-505b-4753-ac75-54569bcc497b.png)\

**Latihan 2**\
Pada latihan 2 saya disuruh membuat balance berkurang sesuai dengan harga item dan jika item pada cart dihapus maka balance akan bertambah lagi. Saya menambahkan beberapa kode pada fungsi handleAddItemToCart dan handleDeleteItemFromCart. Saya menambahkan kode untuk melakukan setState pada balance yang tersedia. Jika menambahkan barang ke cart maka balance sekarang dikurangi dengan balance yang dipunya, sedangkan untuk menghapus item pada cart dilakukan sebaliknya.\
![image](https://user-images.githubusercontent.com/71779362/143260194-e9fcad2c-bd5f-4381-97cc-b9722bf1b2ce.png)
![image](https://user-images.githubusercontent.com/71779362/143268461-2b9a4d8b-50bd-411b-9cfb-ae07f1a022b1.png)
\
Pada gambar diatas saya melakukan pengurangan balance jika menambah barang ke cart, sedangkan gambar dibawah saya melakukan menambahkan kode penambahan balance jika menghapus cart.
![image](https://user-images.githubusercontent.com/71779362/143268668-bd62bc02-c7e7-49e2-9de0-9ca3fd9ae251.png)
![image](https://user-images.githubusercontent.com/71779362/143268707-c87dc6e9-9e4b-47a9-a193-5e70a0892a77.png)

**Latihan 3**\
Pada Latihan 3 jika balance kurang dari harga barang maka akan memunculkan alert. Saya menambahkan conditional pada fungsi add to cart yaitu mengecek apakah balance sekarang lebih besar sama dengan harga item
![image](https://user-images.githubusercontent.com/71779362/143268967-136de8b7-c9ea-458d-ba9b-66689b03a9b6.png)


2. Menurut pemahaman kamu selama pengerjaan tutorial ini, apa perbedaan antara state dan
props?\
  **Jawab**\
 State merupakan data yang tersimpan dalam suatu component dan bersifat private, sehingga data tersebut tidak dapat diakses oleh componen lain. Sedangkan, props merupakan singkatan dari property. Value dari props dapat dipassing seperti atribut HTMl dan biasanya digunakan untuk melakukan komunikasi antar component. 
  
3. Menurut kamu, apakah sebaiknya kita menggunakan component (e.g. List, Item) dalam
React? sebutkan alasannya.\
 **Jawab**\
 Menurut saya sebaiknya kita menggunakan component sehingga dapat mempermudah untuk membaca kodingan. Selain itu, dengan menggunakan component kita dapat menggunakan kode tersebut pada component lain dan jika ada perubahan kodingan, kita hanya mengubah kode dari komponent tersebut tidak harus mengubah kode keseluruhan.
 
4. Apa perbedaan class component dan functional component?\
 **Jawab**\
Pada tutorial ini terlihat bahwa jika menggunakan functional component maka stateless dan jika menggunakan class component maka stateful. Yang berarti pada class component memiliki state pada komponent tersebu, sedangkan functional component tidak memakai state pada komponen tersebut.

5. Dalam react, apakah perbedaan component dan element?\
 **Jawab**\
React element adalah objek yang merepresentasikan DOM yang terdiri dari element-element HTML seperti div dan pada react memanfaatkan satu elemen pada file index.html root yang disebut dengan 'root' DOM. Semua elemen di dalam root DOM tersebut akan di-manage oleh React DOM. Sedangkan react component seperti function pada javascript dan menerima input yang disebut props dan akan mereturn React element. 

### What I did not understand
Pada tutorial ini saya tidak mengerti tentang details dari UserDetail, apakah UserDetails itu merupakan model User bawaan dari Springboot atau bagaimana?.

## Tutorial 6
### What I have learned today
Pada Tutorial 6 saya belajar tentang Security pada Springboot dan bagaimana membuat fitur Login dan Logout. Selain itu, saya belajar tentang bagaimana membuat user ke database dan cara melakukan encrypt pada sebuah data atau string.

### Pertanyaan
1. Jelaskan secara singkat perbedaan Otentikasi dan Otorisasi! Di bagian mana (dalam kode
yang telah anda buat) konsep tersebut diimplementasi?\
**Jawab**
- Otentikasi adalah proses identifikasi penguna apakah pengguna tersebut terdaftar pada database atau tidak. Proses identifikasi dilakukan dengan cara menyamakan username dan password yang dimasukkan dengan yang berada pada database. Konsep ini diimplementasi pada class WebSecurityConfig pada method configAuthentication
- Otorisasi adalah proses menentukan apakah pengguna atau user saat ini diperbolehkan untuk melakukan suatu tugas tertentu atau tidak. Konsep ini diimplementasi pada class WebSecurityConfig pada method configure. Selain itu, konsep ini juga saya gunakan pada bagian Lihat Semua User dan melakukan akses pada pengguna, pada bagian melihat semua user dan melakukan add dan delete user hanya dapat dilakukan oleh user dengan role ADMIN dan yang dapat melakukan akses pada pengguna hanya user yang memiliki role Manager.

2. Apa itu BCryptPasswordEncoder? Jelaskan secara singkat cara kerja dan tujuannya\
  **Jawab**\
  BCryptPasswordEncoder merupakan implementasi untuk melakukan enkripsi ataupun dekripsi pada sebuah password yang menggunakan algoritma bcrypt. BCryptPasswordEncoder memiliki tujuan untuk melakukan enkripsi sebuah password sehingga password tidak dapat dibaca pada database. Cara kerja dari BcryptPasswordEncoder yaitu secara otomatis menghasilkan dan menggunakan random salt untuk menghitung hash, sehingga setiap kali memanggil BCryptPasswordEncoder akan mendapatkan output yang berbeda.
  
3. Apakah penyimpanan password sebaiknya menggunakan encryption atau hashing? Mengapa
demikian?\
 **Jawab**
 - Enkripsi merupakan fungsi dua arah sehingga string yang sudah di enkrispi dapat dibalikkan menjadi string semula dengan melakukan dekripsi. Tetapi untuk melakukan dekripsi dibutuhkan key.
 - Hashing merupakan fungsi satu arah sehingga string semula tidak dapat dipulihkan
 Menurut saya, penyimpanan password lebih baik menggunakan Hashing dikarenakan jika menggunakan enkripsi dan peretas dapat menemukan key pada basis data kita untuk melakukan dekripsi, peretas dapat memperoleh kata sandi asli dengan menggunakan key yang didapatkannya. 
 
4. Jelaskan secara singkat apa itu UUID beserta penggunaannya!\
 **Jawab**\
UUID merupakan singkatan dari Universally Unique Identifier yang merupakan sebuah kombinasi 32 karakter yang dibuat secara acak dengan teknik kusus yang dijamin unik untuk setiap data. Dikarenakan dijamin unik maka UUID sangat cocok untuk dijadikan Primary Key. Penggunaan pada Spring Boot yaitu menambahkan @ sebelum membuat model pada sebuah attribute. @ yang digunakan adalah @GeneratedValue(generator="system-uuid") dan @GenericGenerator(name="system-uuid", strategy = "uuid") dan biasanya uuid digunakan pada attribute id pada model.

5. Apa kegunaan class UserDetailsServiceImpl.java? Mengapa harus ada class tersebut?\
 **Jawab**\
Class UserDetailsServiceImpl digunakan untuk mencari username apakah username tersebut terdapat pada database. Pada Class ini juga terdapat method untuk menyimpan username, password, dan granted authorities yang berisi role dari user tersebut. Lalu, username, password, dan role tersebut disimpan pada User pada UserDetails bawaan Spring boot yang di import dari org.springframework.security.core.userdetails.User sehingga membantu proses otentikasi pengguna.

### What I did not understand
Pada tutorial ini saya tidak mengerti tentang details dari UserDetail, apakah UserDetails itu merupakan model User bawaan dari Springboot atau bagaimana?.

## Tutorial 5
### What I have learned today
Pada Tutorial 5 saya belajar tentang bagaimana menggunakan rest API pada Spring boot dan membuat mock server. Selain itu, saya juga belajar tentang penggunaan Postman.

### Pertanyaan
1. Apa itu Postman? Apa kegunaannya?\
**Jawab**\
Postman adalah sebuah aplikasi yang berfungsi sebagai REST Client untuk uji coba REST API. Kegunaan dari Postman yaitu sebagai tools untuk menguji API yang developer buat. Pada Postman memiliki beberapa fitur salah satunya pada tutorial ini menggunakan Collection, Environments, dan Mock Server. Collection merupakan folder pengelompokan request API yang disimpan. Environments merupakan semacam config untuk menyimpan atribute. Mock Server merupakan untuk membuat server API yang dapat diakses oleh internet.

2. Jelaskan fungsi dari anotasi @JsonIgnoreProperties dan @JsonProperty.\
  **Jawab**
  - @JsonIgnoreProperties : Digunakan dalam tingkat kelas untuk menandai properi atau daftar properti yang akan diabaikan
  - @JsonProperty : Digunakan untuk melakukan serialize atau deserialize nama properti saat kita berurusan dengan getter dan setter non-standar
  
3. Apa kegunaan WebClient?\
 **Jawab**\
 WebClient merupakan interface yang mewakili entry point utama untuk melakukan web request. WebClient merupakan bagian dari modul Spring Web Reactive, dan akan menggantikan Rest Template. Kegunaan webclient untuk melakukan non-blocking, reactive client untuk melakukan permintaan HTTP, dan sebagainya.
 
 4. Apa itu ResponseEntity dan BindingResult? Apa kegunaannya?\
 **Jawab**\
ResponseEntity berguna untuk mewakili seluruh respons HTTP seperti, status code, headers, dan body. Maka dari itu kita dapat menggunakannya untuk mengikonfigurasi response HTTP secara keseluruhan.
BindingResult menyimpan hasil validasi dan binding dan berisi kesalahan yang mungkin dapat terjadi dan jika terjadi kesalahan akan mereturn exception.

### What I did not understand
Pada tutorial ini sepertinya saya cukup paham tentang lab ini.

## Tutorial 4
### What I have learned today
Pada Tutorial 4 saya belajar tentang penggunaan file static, fragment, error handling, dan form handler pada spring boot.

### Pertanyaan
1. Jelaskan perbedaan th:include dan th:replace!\
**Jawab**
Untuk include sama seperti Django yaitu menyisipkan suatu konten ke dalam html, sedangkan untuk replace adalah menggantikan konten yang sudah ada dengan yang ada pada fragmen.

2. Jelaskan apa fungsi dari th:object!\
  **Jawab**
  Untuk mengirimkan object dari form pada html dengan method post atau get, lalu akan diterima oleh controller dengan @ModelAtribute yang menerima atribute dari halaman frontend.
  
3. Jelaskan perbedaan dari * dan $ pada saat penggunaan th:object! Kapan harus dipakai?\
 **Jawab**
 - Pada th:object yang menggunakan $ merupakan variable expression yang mengintegrasikan Spring dengan Thymeleaf.
 - Pada th:object yang menggunakan * merupakan selection expression yang akan mengexecute sesuai objek yang dipilih.

### What I did not understand
Saya tidak mengerti bagaimana cara melakukan binding di Springboot pada bagian select dan option pada Thymeleaf dengan Controller.

## Tutorial 3
### What I have learned today
Pada Tutorial 3 saya belajar tentang JPA pada Spring boot dan melakukan CRUD yang terkoneksi dengan Database MySql.

### Pertanyaan
1. Tolong jelaskan secara singkat apa kegunaan dari anotasi-anotasi yang ada pada model
(@AllArgsConstructor, @NoArgsConstructor, @Setter, @Getter, @Entity, @Table)\
**Jawab**
- **AllArgsConstructor**, adalah anotasi untuk mengenerate konstruktor dengan 1 parameter dari setiap atribut yang ada pada kelas.
- **@NoArgsConstructor**, adalah anotasi untuk mengenerate konstruktor tanpa parameter,
- **@Setter dan @Getter**, adalah anotasi untuk membuat method setter dan getter default secara otomatis pada setiap atribut yang ada pada kelas.
- **@Entity**, adalah anotasi yang menandakan suatu class tersebut merupakan entitas JPA.
- **@Table**, adalah anotasi untuk membuat table pada SQL

2. Pada class BioskopDB, terdapat method findByNoBioskop, apakah kegunaan dari method
tersebut?\
**Jawab**\
method findByNoBioskop merupakan methode untuk mencari bioskop berdasarkan no Bioskopnya dan mereturn suatu object bioskop.

3. Jelaskan perbedaan kegunaan dari anotasi @JoinTable dan @JoinColumn.\
**Jawab**\
@JoinTable akan menyimpan id dari kedua table ke dalam table terpisah sedangkan @JoinColum akan menyimpan id dari table lain di kolom baru.

4. Pada class PenjagaModel, digunakan anotasi @JoinColumn pada atribut bioskop, apa
kegunaan dari name, referencedColumnName, dan nullable dalam anotasi tersebut? dan apa
perbedaan nullable dan penggunaan anotasi @NotNul\
**Jawab**
- Kegunaan dari **name** adalah membuat nama kolom atau table pada database sesuai dengan **name**
- Kegunaan dari **referencedColumnName** adalah menentukan kolom yang di rujuk yang menjadi foreign key. Pada PenjagaModel yaitu membuat kolom no_bioskop dengan referensi dari model Bioskop dengan nama kolom noBioskop
- Kegunaan dari **nullable** adalah membuat kolom tersebut dapat berisi null. 
- Perbedaan dari **nullable** dengan **@NotNull** yaitu @NotNull merupakan anotasi yang terkait dengan Java. Java akan mengeluarkan exception ketika attribute tersebut belum di set, sedangkan nullable merupakan bagian dari @Column dan membuat kolom tersebut tidak menjadi null yang merupakan constraint dari database. Maka dari itu dengan menggunakan nullable tidak akan terjadi eror jika data belum dikirim ke database.

5. Jelaskan kegunaan FetchType.LAZY, CascadeType.ALL, dan FetchType.EAGER\
**Jawab**
- FetchType.LAZY akan melakukan load data namun tidak akan melakukan load semua collection object/child saat object parent di fetch
- FetchType.EAGER akan melakukan load semua collection/child sesaat setelah object parent di fetch
- CascadeType.ALL akan melakukan semua operasi tanpa memperdulikan constraint yang diperintahkan

### What I did not understand
Kurang paham dengan mempassing suatu object dari front end ke back end pad SpringBoot

## Tutorial 2
### What I have learned today
Pada Tutorial 2 saya belajar membuat service dan CRUD pada Spring Boot.

### Pertanyaan
1. Cobalah untuk menambahkan sebuah Bioskop dengan mengakses link berikut: http://localhost:8080/bioskop/add?idBioskop=1&namaBioskop=Bioskop%20PAPA%20APAP&alamat=Maung%20Fasilkom&noTelepon=081xxx&jumlahStudio=10 
Apa yang terjadi? Jelaskan mengapa hal tersebut dapat terjadi
**Jawab**
Akan terjadi Whitelabel error page saat membuka link tersebut. Hal itu dikarenakan view yang di return pada Controller belum dibuat.

2. Menurut kamu anotasi @Autowired pada class Controller tersebut
merupakan implementasi dari konsep apa? Dan jelaskan secara singkat cara kerja
@Autowired tersebut dalam konteks service dan controller yang telah kamu buat
**Jawab**
Autowired merupakan implementasi dari konsep Dependency Injection. Dengan menggunakan autowired seperti pada Lab ini yaitu "private BioskopService bioskopService;" sehingga kita dapat mengakses constructors yang berada di service di controller.

3. Cobalah untuk menambahkan sebuah Bioskop dengan mengakses link
berikut: http://localhost:8080/bioskop/add?idBioskop=1&namaBioskop=Bioskop%20PAPA%20APAP&alamat=Maung%20Fasilkom&noTelepon=081xxx Apa yang terjadi? Jelaskan
mengapa hal tersebut dapat terjadi.
**Jawab**
Akan terjadi error dikarenakan parameter pada link tersebut kurang. Parameter tersebut yaitu Jumlah Studio.

4. Jika Papa APAP ingin melihat Bioskop dengan nama Bioskop Maung, link apa yang harus diakses?
**Jawab**
Sebelumnya kita harus mengetahui id dari Bioskop yang memiliki nama Bioskop Maung, id tersebut dapat dilihat pada http://localhost:8080/bioskop/viewall. Misalnya Bioskop Maung mempunyai id = 1, kita dapat melihat detail dari Bioskop Maung dengan mengakses http://localhost:8080/bioskop/view/id-bioskop/1 (Latihan) atau http://localhost:8080/bioskop/view?idBioskop=1

5. Tambahkan 1 contoh Bioskop lainnya sesukamu. Lalu cobalah untuk mengakses http://localhost:8080/bioskop/viewall , apa yang akan ditampilkan? Sertakan
juga bukti screenshotmu.
**Jawab**
![image](https://user-images.githubusercontent.com/71779362/133429376-e2f861cf-1203-4f39-851b-e50758bec684.png)

### What I did not understand
Saat mengerjakan latihan saya ingin menggunakan update dengan menggunakan textbox, tetapi saya tidak mengerti bagaimana passing parameter dari value textbox ke href url.

## Tutorial 1
### What I have learned today
Pada Tutorial 1 saya belajar tentang dunia pergit-tan dan java spring boot.

### Github
1. Apa itu Issue Tracker? Apa saja masalah yang dapat diselesaikan dengan Issue Tracker?
**Jawab**
Dengan adanya issue tracker kita dapat mencatat masalah sehingga masalah atau permintaan perubahan dapat di track dengan mudah. Masalah yang dapat diselesaikan dengan Issue Tracker yaitu seperti fitur baru, ada bug, improvement pada suatu fitur dan sebagainya.

2. Apa perbedaan dari git merge dan git merge --squash?
**Jawab**
Git merge merupakan default merge dari githubnya yang akan mengambil semua commit dari sebuah pull request untuk menggabungkan 2 branch sedangkan git merge --squash sama seperti git merge namun setiap commit dari branch tersebut yang akan digabungkan menjadi 1 commit di main branch.

3. Apa keunggulan menggunakan Version Control System seperti Git dalam pengembangan
suatu aplikasi?
Dengan menggunakan Version Control System dapat membantu kinerja developer untuk mengelola suatu aplikasi dan dapat melakukan konfigurasi dari suatu aplikasi. Dengan adanya VCS developer dapat melacak perubahan kode dalam suatu aplikasi. Selain itu, pengerjaan jadi lebih terstruktur karena dengan adanya VCS pembuatan kode tidak akan saling bertabrakan dan tidak akan terjadi penimpaan kode dan bertanggung jawab atas perubahan yang setiap developer lakukan.  

### Spring
4. Apa itu library & dependency?
**Jawab**
Depedensi merupakan kode yang bergantung dengan kode lain. Library merupakan sekelompok kelas yang melakukan fungsi serupa dan dikelompokkan bersama sehingga mereka dapat digunakan pada setiap projek.

5. Apa itu Maven? Mengapa kita menggunakan Maven? Apakah ada alternatif dari Maven?
**Jawab**
Maven merupakan Java Build Tools yang berguna bagi programmer java untuk pengembangan aplikasi seperti pembuatan project. Maven memiliki banyak keunggulan sehingga kita memakai maven seperti maven dapat membuat struktur dari projek itu sendiri sehingga projek tersebut dapat dibuka oleh berbagai IDE dan IDE tersebut akan mendefinisikan projek yang dibuat oleh maven dan akan melakukan sync terhadap dependensinya sehingga memanage dependensi dengan maven itu mudah. Alternatif dari Maven yaitu Ant dan Gradle. 

6. Selain untuk pengembangan web, apa saja yang bisa dikembangkan dengan Spring
framework?
**Jawab**
Selain pengembangan web Spring Framework dapat digunakan untuk membuat aplikasi, pengaturan data ke database, membuat remote access, membuat mailing, dan sebagainya.

7. Apa perbedaan dari @RequestParam dan @PathVariable? Kapan sebaiknya
menggunakan @RequestParam atau @PathVariable?
**Jawab**
@RequestParam untuk parameter query GET seperti "is-palindrome?kalimat=isi" yang mengekstrak nilai string query yang berguna untuk sorting, filtering, dan sebagainya. Sedangkan @PathVariable untuk mendapatkan objek individual seperti "is-palindrome/isi" disini kita mendapatkan parameter isi sebagai objek individual. Sebaiknya kita memakai @RequestParam untuk melakukan sorting ataupun filtering sedangkan untuk mengambil 1 objek seperti id disarankan menggunakan @PathVariable

### What I did not understand
Kenapa kita menggunakan Java Spring Framework?











