package ProjekAkhir;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author Viabel
 */

/*
    Di sini letakkan catatan, apa yang kurang atau semacamnya
    [Belum]
        -. 

    [Butuh Konfirmasi]
        -. 

*/

public class Main {
    
    // Deklarasi Variabel "Global"
    static InputStreamReader isr = new InputStreamReader(System.in); //Untuk inputan
    static BufferedReader br = new BufferedReader(isr); //Untuk inputan
    static Admin Adminian = new Admin(0, "Fuad", "NTLee", 
                                   "SolidSolid", "NTLee@gmail.com"); //Untuk object dari class Admin
    static String Menu = "Login"; //Untuk penampil menu terpisah
    static int Opsi; //Untuk menyimpan segala macam pilihan tipe integer, misal: Pilihan Menu
    static int capIDAkun = 1; //Untuk menyimpan ID terakhir tersimpan di Data Akun
    
    // Deklarasi Variabel Array
    static ArrayList<Akun> DaftarAkun = new ArrayList<>(); //Untuk menyimpan data toko
    static ArrayList<Toko> DaftarToko = new ArrayList<>(); //Untuk menyimpan data toko
    
    public static int CheckInt(){
        try{
            return Integer.parseInt(br.readLine());
        }
        
        catch(IOException | NumberFormatException e){
            return -1;
        }
    }
    
    
    // Fungsi mencari index dari suatu Username
    public static int USNSequential(String Username){
        int i = 0;
        for (Akun dataAkun : DaftarAkun){
            if(Username.equals(dataAkun.getUsn())) return i;
            i += 1;
        }
        return -1;
    }
    
    // Prosedur Untuk Clear Screen
    public static void clear() throws IOException, InterruptedException{
        if (System.console() != null){
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            return;
        }
        
        int i;
        for(i = 0; i <= 30; i++){
            System.out.print("\n");
        }
    }

    
    // Hapus nanti
    public static Seller DefaultSellerAcc(String UsnA, String PassA, String NamaA, String EmailA, String Nomor, String Addr){
        Seller User = new Seller();
        
        User.setID(capIDAkun);
        User.setUsn(UsnA);
        User.setPass(PassA);
        User.setNama(NamaA);
        User.setEmail(EmailA);
        capIDAkun+=1;
        
        return User;
    }
    
    
    // --------------------------------------- MAIN ZONE ---------------------------------------
    public static void main(String[] args) throws IOException, InterruptedException {
        DaftarAkun.add(Adminian);
        
        // Untuk Uji Coba, Mohon hapus nanti
        DaftarAkun.add(DefaultSellerAcc("Agus", "Sun1004", "Tina", "AgusNumeroUno@Naver.com", "1004", "Bumbum"));
        
        DaftarToko.add(new Toko(1, "Toko Sukamiskin", 1));
        DaftarToko.get(0).TambahProduk(new Album(1, "Asterium", "PLAVE"));
        DaftarToko.get(0).TambahProduk(new Album(2, "Espergo", "PLAVE"));
        DaftarToko.get(0).TambahProduk(new LightStick(3, "LightStick EXO", "EXO", true));
        
        // DaftarToko.get(0).TampilDeskripsi();
        // DaftarToko.get(0).TampilProduk();
        
        // Visual Program Berjalan
        while(true){
            
            
            // --------------------------------------- BAGIAN MENU LOGIN ---------------------------------------
            if(Menu.equals("Login")){
                // Isi Menu Login
                clear();
                System.out.println("""
                                    | ------------------------------------------ |
                                    |   //      Selamat Datang, Kawan :)     \\\\  |
                                    |  ||                                     || |
                                    |  ||           (1) Login                 || |
                                    |  ||           (2) Register              || |
                                    |  ||           (3) Keluar                || |
                                    |   \\\\                                   //  |
                                    | ------------------------------------------ |
                                        """);
                
                System.out.print("\t\t :>> ");
                Opsi = CheckInt();
                
                
                // Respon Menu Login
                switch (Opsi) {
                    
                    // Logun
                    case 1 -> {
                        clear();
                        System.out.println("""
                                    | ------------------------------------------ |
                                    |   //      Silahkan Login, Kawan :)     \\\\  |
                                    |  ||                                     || |
                                    |  ||           Username  :               || |
                                    |  ||           Password  :               || |
                                    |  ||                                     || |
                                    |   \\\\                                   //  |
                                    | ------------------------------------------ |
                                            """);

                        System.out.print("Masukkan Username: ");
                        String Username = br.readLine();

                        clear();
                        System.out.println("""
                                    | ------------------------------------------ |
                                    |   //      Silahkan Login, Kawan :)     \\\\  |
                                    |  ||                                     || |
                                                Username  :   """ + " " + Username + """      

                                    |  ||       Password  :                   || |
                                    |  ||                                     || |
                                    |   \\\\                                   //  |
                                    | ------------------------------------------ |
                                            """);
                        
                        System.out.print("Masukkan Password: ");
                        String Password = br.readLine();
                        
                        clear();
                        System.out.println("""
                                    | ------------------------------------------ |
                                    |   //      Silahkan Login, Kawan :)     \\\\  |
                                    |  ||                                     || |
                                                Username  : """ + " " + Username + """      
                                                                
                                                \t    Password  :   """ + " " + Password + """ 

                                    |  ||                                     || |
                                    |   \\\\                                   //  |
                                    | ------------------------------------------ |
                                            """);

                        // Anti Null Value
                        if(Username.equals("") || Password.equals("")) break;
                        
                        // Cek Username dan Password
                        Opsi = USNSequential(Username);
                        
                        if(Opsi == -1){
                            System.out.println("\n\tUsername atau Password salah");
                            br.readLine();
                            break;
                        }
                        
                        if(!DaftarAkun.get(Opsi).getPass().equals(Password)){
                            System.out.println("\n\tUsername atau Password salah");
                            br.readLine();
                            break;
                        }

                        Menu = DaftarAkun.get(Opsi).Otoritas;
                        System.out.println("\n\tSelamat Datang, " + Menu + " " +  Username + " !");
                        br.readLine();
                        
                    }
                    
                    // Registus
                    case 2 -> {
                        Customer User = new Customer();
                        User.TambahAkun();
                        if(User.getUsn() == null) break;
                        DaftarAkun.add(User);
                        capIDAkun+=1;
                        System.out.println(" Akun Berhasil Ditambahkan! :KEKW:");
                        }
                    
                    // Exto
                    case 3 -> System.exit(0);
                    default -> {
                        continue;
                    }
                }
                
                
                // Kembali ke Awal jika Menu masih "Login"
                if(Menu.equals("Login")){
                    continue;
                }
                
            }

           
            switch (Menu) {
            // --------------------------------------- BAGIAN MENU CUSTOMER ---------------------------------------
                case "Customer" ->
                    // Isi Menu Utama Untuk Customer
                    System.out.println("""
                                       Adalah Menu Customer
                                       (1)-. Menuju Toko
                                       (2)-. Menuju Tas Belanjaku
                                       (3)-. Baca Riwayat Pembelianku
                                       (4)-. Ubah Profil
                                       (5)-. Log Out
                                       """);
                    
            // --------------------------------------- BAGIAN MENU ADMIN ---------------------------------------
                case "Admin" -> {
                    //Isi Menu Utama Untuk Admin
                    System.out.println("""
                                        Adalah Menu Admin
                                        (1) Manajemen Toko
                                        (2) Manajemen Seller
                                        (3) Riwayat Pembelian
                                        (4) Ubah Profil
                                        (5) Log Out
                                        """);
                    
                    System.out.print(" :>> ");
                    Opsi = CheckInt();
                    
                    switch (Opsi) {
                        case 1 -> Toko.TampilSemuaToko();
                        case 2 -> {
                        Adminian.manajemenSeller();
                    }
                        case 3 -> {
                        //admin.riwayatPembelian();
                    }
                        case 4 -> {
                        Adminian.ubahProfil();
                    }
                        case 5 -> Menu = "Login";
                    }
                                    
                }
                    
            // --------------------------------------- BAGIAN MENU SELLER ---------------------------------------
                case "Seller" -> {
                    //Isi Menu Utama Untuk Seller
                    System.out.println("""
                                        Adalah Menu Seller
                                        (1) Tokoku
                                        (2) Ubah Profil
                                        (3) Log Out
                                        """);
                    
                    System.out.print(" :>> ");
                    Opsi = CheckInt();
                    
                    switch (Opsi) {
                        case 1 -> Toko.ManajemenToko();
                        case 2 -> {
                        
                    }
                        case 3 -> Menu = "Login";
                        default -> {
                    }
                    }
                }
                    
                default -> {
                }
            }
            
            
                    
        }
    }
}
