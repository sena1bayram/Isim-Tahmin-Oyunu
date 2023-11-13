package com.example.ismehiroyunu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class NormalOyunActivity extends AppCompatActivity {
    private String[] iller = {"Adana", "Adıyaman", "Afyonkarahisar", "Ağrı", "Amasya", "Ankara", "Antalya", "Artvin", "Aydın", "Balıkesir",
            "Bilecik", "Bingöl", "Bitlis", "Bolu", "Burdur", "Bursa", "Çanakkale", "Çankırı", "Çorum", "Denizli", "Diyarbakır",
            "Edirne", "Elazığ", "Erzincan", "Erzurum", "Eskişehir", "Gaziantep", "Giresun", "Gümüşhane", "Hakkâri", "Hatay",
            "Isparta", "Mersin", "İstanbul", "İzmir", "Kars", "Kastamonu", "Kayseri", "Kırklareli", "Kırşehir", "Kocaeli",
            "Konya", "Kütahya", "Malatya", "Manisa", "Kahramanmaraş", "Mardin", "Muğla", "Muş", "Nevşehir", "Niğde", "Ordu",
            "Rize", "Sakarya", "Samsun", "Siirt", "Sinop", "Sivas", "Tekirdağ", "Tokat", "Trabzon", "Tunceli", "Şanlıurfa",
            "Uşak", "Van", "Yozgat", "Zonguldak", "Aksaray", "Bayburt", "Karaman", "Kırıkkale", "Batman", "Şırnak", "Bartın",
            "Ardahan", "Iğdır", "Yalova", "Karabük", "Kilis", "Osmaniye", "Düzce"};
    private Random rndIl, rndHarf;
    private int rndNumber, rndNumberHarf, baslangicHarfSayisi;
    private String gelenİl, ilBoyut, editTxtGelenTahmin;
    private TextView txtIlBilgi, txtIl,bolumPuan;
    private ArrayList<Character> ilHarfleri;
    private EditText editTxtTahmin;
    private float maxPuan=100, azaltilicakPuan,toplamPuan=0, bolumToplamPuan=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_oyun);

        txtIl = (TextView) findViewById(R.id.textViewIlN);
        txtIlBilgi = (TextView) findViewById(R.id.textViewBilgiN);
        editTxtTahmin = (EditText) findViewById(R.id.editTxtTahminN);
        bolumPuan=(TextView) findViewById(R.id.txtBolumToplamPuanN);

        rndHarf = new Random();
        randomDegerBelirle();

    }

    public void btnHarfAlN(View v) {
        if(ilHarfleri.size()>0) {
            randomHarfAl();
            toplamPuan -=azaltilicakPuan;

            Toast.makeText(getApplicationContext(), "Kalan Puan: " + toplamPuan, Toast.LENGTH_SHORT).show();
        }
        else {
            System.out.println("harf kalmadı");
        }

    }

    public void btnTahminEtN(View v) {
        editTxtGelenTahmin=editTxtTahmin.getText().toString();

        if(!TextUtils.isEmpty(editTxtGelenTahmin)){
            if (editTxtGelenTahmin.equals(gelenİl)){
                bolumToplamPuan += toplamPuan;

                Toast.makeText(getApplicationContext(), "Tebrikler! Doğru Cevapladınız ", Toast.LENGTH_SHORT).show();
                randomDegerBelirle();
                editTxtTahmin.setText("");
                bolumPuan.setText("Toplam Bölüm Puanı: "+ String.valueOf(bolumToplamPuan));

            }
            else {
                System.out.println("Yanlış Tahminde Bulundunuz");
            }
        }
        else {
            System.out.println("Boş Olamaz");
        }
    }

    private void randomDegerBelirle(){
        ilBoyut="";
        rndIl = new Random();
        rndNumber = rndIl.nextInt(iller.length);
        gelenİl = iller[rndNumber];
        System.out.println(gelenİl);

        txtIlBilgi.setText(gelenİl.length() + " Harfli İl");


        if (gelenİl.length()<5){
            baslangicHarfSayisi=0;
        }
        if(gelenİl.length()>=5 && gelenİl.length()<=7){
            baslangicHarfSayisi=1;
        }
        if (gelenİl.length()>=8 && gelenİl.length()<10){
            baslangicHarfSayisi=2;
        }
       if (gelenİl.length()>=10) {
            baslangicHarfSayisi=3;
        }


        for (int i = 0; i < gelenİl.length(); i++) {
            if (i < gelenİl.length() - 1) {
                ilBoyut += "_ ";
            } else {
                ilBoyut += "_";
            }
        }

        txtIl.setText(ilBoyut);
        ilHarfleri = new ArrayList<>();

        for (char c : gelenİl.toCharArray()) {
            ilHarfleri.add(c);
        }

        for (int i=0;i<baslangicHarfSayisi;i++){
            randomHarfAl();
        }

        azaltilicakPuan = maxPuan/ilHarfleri.size();
        toplamPuan = maxPuan;
    }

    private void randomHarfAl(){
            rndNumberHarf=rndHarf.nextInt(ilHarfleri.size());

            String[] txtHarfler = txtIl.getText().toString().split(" ");
            char[] gelenIlHarfler= gelenİl.toCharArray();

            for (int i = 0; i < gelenİl.length(); i++) {
                if (txtHarfler[i].equals("_") && gelenIlHarfler[i] == ilHarfleri.get(rndNumberHarf)) {
                    txtHarfler[i]=String.valueOf(ilHarfleri.get(rndNumberHarf));
                    ilBoyut="";

                    for (int j=0;j<gelenİl.length();j++){
                        if(j==i){
                            ilBoyut+= txtHarfler[j]+ " ";
                        }
                        else if(j<gelenİl.length() - 1){
                            ilBoyut+=txtHarfler[j]+ " ";
                        }
                        else {
                            ilBoyut+=txtHarfler[j];
                        }
                    }
                    break;
                }

            }

            txtIl.setText(ilBoyut);
            ilHarfleri.remove(rndNumberHarf);
    }
}