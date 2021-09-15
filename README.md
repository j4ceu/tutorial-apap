# Tutorial APAP
## Authors
Sutan Raihan Maulaya - 1906305820 - C

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









