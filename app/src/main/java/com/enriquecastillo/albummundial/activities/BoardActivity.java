package com.enriquecastillo.albummundial.activities;

import android.content.DialogInterface;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.enriquecastillo.albummundial.R;
import com.enriquecastillo.albummundial.adapter.BoardAdapter;
import com.enriquecastillo.albummundial.fragments.CheckedFragment;
import com.enriquecastillo.albummundial.fragments.NoCheckedFragment;
import com.enriquecastillo.albummundial.fragments.ListaFragment;
import com.enriquecastillo.albummundial.model.Board;
import com.enriquecastillo.albummundial.model.Stamp;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import io.realm.Realm;
import io.realm.RealmResults;

public class BoardActivity extends AppCompatActivity  {

    private Realm realm;
    private RealmResults<Board> boards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        setIconOnActivity();

        realm = Realm.getDefaultInstance();
        boards = realm.where(Board.class).findAll();

        if (boards.size() == 0) {
            createNewBoardsAndStamps();
        }

        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setDefaultTab(R.id.list_tab);

        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(int tabId) {
                switch (tabId){
                    case R.id.list_tab:
                        ListaFragment listaFragment = new ListaFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container , listaFragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                                .addToBackStack(null).commit();
                        break;
                    case R.id.checked_tab:
                        CheckedFragment checkedFragment = new CheckedFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container , checkedFragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                                .addToBackStack(null).commit();

                        break;
                    case R.id.nochecked_tab:
                        NoCheckedFragment noCheckedFragment = new NoCheckedFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container , noCheckedFragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                                .addToBackStack(null).commit();
                        break;
                }
            }
        });

    }


    private void setIconOnActivity() {
        getSupportActionBar().setTitle("  Álbum Mundial");
        getSupportActionBar().setIcon(R.mipmap.ic_launcher_ballon);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_option, menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.menu_delete_all:
//                showAlertDialogDelete("Borrar Todo","Vas a borrar toda las laminas que posees.");
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }

    private void showAlertDialogDelete(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (title != null) builder.setTitle(title);
        if (message != null) builder.setMessage(message);

        builder.setPositiveButton("Borrar Todo", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                realm.beginTransaction();
                realm.deleteAll();
                realm.commitTransaction();
            }
        });

        builder.setNegativeButton("Cancelar", null);

        builder.create().show();

    }

    private void createNewBoardsAndStamps() {
        realm.beginTransaction();
        Board board = new Board("Principal", "(1 - 7)", R.mipmap.ic_world_cup);
        createStamp(board);
        realm.copyToRealm(board);
        Board board1 = new Board("Estadios", "(8 - 19)", R.mipmap.ic_soccer_filed);
        createStamp(board1);
        realm.copyToRealm(board1);
        Board board2 = new Board("Rusia", "(20 - 39)", R.drawable.russia);
        createStamp(board2);
        realm.copyToRealm(board2);
        Board board3 = new Board("Arabia Saudita", "(40 - 59)" ,R.drawable.saudi_arabia);
        createStamp(board3);
        realm.copyToRealm(board3);
        Board board4 = new Board("Egipto",  "(60 - 79)" ,R.drawable.egypt);
        createStamp(board4);
        realm.copyToRealm(board4);
        Board board5 = new Board("Uruguay",  "(80 - 99)" ,R.drawable.uruguay);
        createStamp(board5);
        realm.copyToRealm(board5);
        Board board6 = new Board("Portugal",  "(100 - 119)" ,R.drawable.portugal);
        createStamp(board6);
        realm.copyToRealm(board6);
        Board board7 = new Board("España", "(120 - 139)",R.drawable.spain);
        createStamp(board7);
        realm.copyToRealm(board7);
        Board board8 = new Board("Marruecos", "(140 - 159)",R.drawable.morocco);
        createStamp(board8);
        realm.copyToRealm(board8);
        Board board9 = new Board("Irán", "(160 - 179)" ,R.drawable.iran);
        createStamp(board9);
        realm.copyToRealm(board9);
        Board board10 = new Board("Francia", "(180 - 199)" ,R.drawable.france);
        createStamp(board10);
        realm.copyToRealm(board10);
        Board board11 = new Board("Australia", "(200 - 219)" ,R.drawable.australia);
        createStamp(board11);
        realm.copyToRealm(board11);
        Board board12 = new Board("Perú", "(220 - 239)" ,R.drawable.peru);
        createStamp(board12);
        realm.copyToRealm(board12);
        Board board13 = new Board("Dinamarca", "(240 - 259)" ,R.drawable.denmark);
        createStamp(board13);
        realm.copyToRealm(board13);
        Board board14 = new Board("Argentina", "(260 - 279)" ,R.drawable.argentina);
        createStamp(board14);
        realm.copyToRealm(board14);
        Board board15 = new Board("Islandia", "(280 - 299)" ,R.drawable.iceland);
        createStamp(board15);
        realm.copyToRealm(board15);
        Board board16 = new Board("Croacia", "(300 - 319)" ,R.drawable.croatia);
        createStamp(board16);
        realm.copyToRealm(board16);
        Board board17 = new Board("Nigeria", "(320 - 339)" ,R.drawable.nigeria);
        createStamp(board17);
        realm.copyToRealm(board17);
        Board board18 = new Board("Brasil", "(340 - 359)" ,R.drawable.brazil);
        createStamp(board18);
        realm.copyToRealm(board18);
        Board board19 = new Board("Suiza", "(360 - 379)" ,R.drawable.switzerland);
        createStamp(board19);
        realm.copyToRealm(board19);
        Board board20 = new Board("Costa Rica", "(380 - 399)" ,R.drawable.costa_rica);
        createStamp(board20);
        realm.copyToRealm(board20);
        Board board21 = new Board("Serbia", "(400 - 419)",R.drawable.serbia);
        createStamp(board21);
        realm.copyToRealm(board21);
        Board board22 = new Board("Alemania", "(420 - 439)" ,R.drawable.germany);
        createStamp(board22);
        realm.copyToRealm(board22);
        Board board23 = new Board("México", "(440 - 459)" ,R.drawable.mexico);
        createStamp(board23);
        realm.copyToRealm(board23);
        Board board24 = new Board("Suecia", "(460 - 479)" ,R.drawable.sweden);
        createStamp(board24);
        realm.copyToRealm(board24);
        Board board25 = new Board("Corea", "(480 - 499)" ,R.drawable.south_korea);
        createStamp(board25);
        realm.copyToRealm(board25);
        Board board26 = new Board("Bélgica", "(500 - 519)" ,R.drawable.belgium);
        createStamp(board26);
        realm.copyToRealm(board26);
        Board board27 = new Board("Panamá", "(520 - 539)" ,R.drawable.panama);
        createStamp(board27);
        realm.copyToRealm(board27);
        Board board28 = new Board("Túnez", "(540 - 559)" ,R.drawable.tunisia);
        createStamp(board28);
        realm.copyToRealm(board28);
        Board board29 = new Board("Inglaterra", "(560 - 579)" ,R.drawable.england);
        createStamp(board29);
        realm.copyToRealm(board29);
        Board board30 = new Board("Polonia", "(580 - 599)" ,R.drawable.republic_of_poland);
        createStamp(board30);
        realm.copyToRealm(board30);
        Board board31 = new Board("Senegal", "(600 - 619)" ,R.drawable.senegal);
        createStamp(board31);
        realm.copyToRealm(board31);
        Board board32 = new Board("Colombia", "(620 - 639)" ,R.drawable.colombia);
        createStamp(board32);
        realm.copyToRealm(board32);
        Board board33 = new Board("Japón", "(640 - 659)" ,R.drawable.japan);
        createStamp(board33);
        realm.copyToRealm(board33);
        Board board34 = new Board("Leyendas", "(660 - 669)",R.mipmap.ic_soccer_team);
        createStamp(board34);
        realm.copyToRealm(board34);
        realm.commitTransaction();

    }

    private void createStamp(Board board) {
        switch (board.getId()) {
            case 1:
                for (int i = 1; i < 8; i++)
                    createStamps(i, board);
                break;
            case 2:
                for (int i = 8; i < 20; i++)
                    createStamps(i, board);
                break;
            case 3:
                for (int i = 20; i < 40; i++)
                    createStamps(i, board);
                break;
            case 4:
                for (int i = 40; i < 60; i++)
                    createStamps(i, board);
                break;
            case 5:
                for (int i = 60; i < 80; i++)
                    createStamps(i, board);
                break;
            case 6:
                for (int i = 80; i < 100; i++)
                    createStamps(i, board);
                break;
            case 7:
                for (int i = 100; i < 120; i++)
                    createStamps(i, board);
                break;
            case 8:
                for (int i = 120; i < 140; i++)
                    createStamps(i, board);
                break;
            case 9:
                for (int i = 140; i < 160; i++)
                    createStamps(i, board);
                break;
            case 10:
                for (int i = 160; i < 180; i++)
                    createStamps(i, board);
                break;
            case 11:
                for (int i = 180; i < 200; i++)
                    createStamps(i, board);
                break;
            case 12:
                for (int i = 200; i < 220; i++)
                    createStamps(i, board);
                break;
            case 13:
                for (int i = 220; i < 240; i++)
                    createStamps(i, board);
                break;
            case 14:
                for (int i = 240; i < 260; i++)
                    createStamps(i, board);
                break;
            case 15:
                for (int i = 260; i < 280; i++)
                    createStamps(i, board);
                break;
            case 16:
                for (int i = 280; i < 300; i++)
                    createStamps(i, board);
                break;
            case 17:
                for (int i = 300; i < 320; i++)
                    createStamps(i, board);
                break;
            case 18:
                for (int i = 320; i < 340; i++)
                    createStamps(i, board);
                break;
            case 19:
                for (int i = 340; i < 360; i++)
                    createStamps(i, board);
                break;
            case 20:
                for (int i = 360; i < 380; i++)
                    createStamps(i, board);
                break;
            case 21:
                for (int i = 380; i < 400; i++)
                    createStamps(i, board);
                break;
            case 22:
                for (int i = 400; i < 420; i++)
                    createStamps(i, board);
                break;
            case 23:
                for (int i = 420; i < 440; i++)
                    createStamps(i, board);
                break;
            case 24:
                for (int i = 440; i < 460; i++)
                    createStamps(i, board);
                break;
            case 25:
                for (int i = 460; i < 480; i++)
                    createStamps(i, board);
                break;
            case 26:
                for (int i = 480; i < 500; i++)
                    createStamps(i, board);
                break;
            case 27:
                for (int i = 500; i < 520; i++)
                    createStamps(i, board);
                break;
            case 28:
                for (int i = 520; i < 540; i++)
                    createStamps(i, board);
                break;
            case 29:
                for (int i = 540; i < 560; i++)
                    createStamps(i, board);
                break;
            case 30:
                for (int i = 560; i < 580; i++)
                    createStamps(i, board);
                break;
            case 31:
                for (int i = 580; i < 600; i++)
                    createStamps(i, board);
                break;
            case 32:
                for (int i = 600; i < 620; i++)
                    createStamps(i, board);
                break;
            case 33:
                for (int i = 620; i < 640; i++)
                    createStamps(i, board);
                break;
            case 34:
                for (int i = 640; i < 660; i++)
                    createStamps(i, board);
                break;
            case 35:
                for (int i = 660; i < 670; i++)
                    createStamps(i, board);
                break;
        }
    }

    private void createStamps(int counter, Board board) {
        Stamp stamp = new Stamp(counter, false);
        board.getStamps().add(stamp);
    }

}
