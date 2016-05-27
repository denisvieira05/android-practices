package org.js.denisvieira.criandonotificacoes;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

/**
 * Created by denisvieira on 23/05/16.
 */
public class NotificationUtils {
    public static final String ACAO_DELETE = "org.js.denisvieira.criandonotificacoes.DELETE_NOTIFICACAO";
    public static final String ACAO_NOTIFICACAO = "org.js.denisvieira.criandonotificacoes.ACAO_NOTIFICACAO";

    private static PendingIntent criarPendingIntent (Context ctx, String Texto,int id){
        Intent resultIntent = new Intent(ctx, DetalheActivity.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(ctx);
        stackBuilder.addParentStack(DetalheActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        return stackBuilder.getPendingIntent(id,PendingIntent.FLAG_UPDATE_CURRENT);
    }

    public static void criarNotificacaoSimples(Context ctx, String texto, int id){
        PendingIntent resultPendingIntent = criarPendingIntent(ctx,texto,id);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(ctx)
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setSmallIcon(R.drawable.ic_notificacao)
                    .setContentTitle("Simples "+ id);

        NotificationManagerCompat nm = NotificationManagerCompat.from(ctx);
        nm.notify(id, mBuilder.build());
    }

    public static void criarNotificacaoCompleta(Context ctx, String texto, int id){
        Uri uriSom = Uri.parse("android.resource://"+ctx.getPackageName()+"/raw/som_notificacao");
        PendingIntent pitAcao = PendingIntent.getBroadcast(ctx,0,new Intent(ACAO_NOTIFICACAO),0);
        PendingIntent pitDelete = PendingIntent.getBroadcast(ctx,0,new Intent(ACAO_DELETE),0);
        Bitmap largeIcon = BitmapFactory.decodeResource(ctx.getResources(),R.mipmap.ic_launcher);
        PendingIntent pitNotificacao = criarPendingIntent(ctx, texto, id);
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(ctx)
                .setSmallIcon(R.drawable.ic_notificacao)
                .setColor(Color.RED)
                .setContentTitle("Completa")
                .setContentText(texto)
                .setTicker("Chegou uma notificação")
                .setWhen(System.currentTimeMillis())
                .setLargeIcon(largeIcon)
                .setContentIntent(pitNotificacao)
                .setLights(Color.BLUE,1000,5000)
                .setSound(uriSom)
                .setVibrate(new long[]{100,500,200,800})
                .addAction(R.drawable.ic_acao_notificacao,"AçãoCustomizada",pitAcao)
                .setNumber(id)
                .setSubText("Subtexto");

        NotificationManagerCompat nm = NotificationManagerCompat.from(ctx);
        nm.notify(id,mBuilder.build());
    }

    public static void criarNotificacaoBig(Context ctx, int idNotificacao){
        int numero = 5;
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();

        inboxStyle.setBigContentTitle("Mensagens não lidas");
        for(int i = 1; i <= numero; i++){
            inboxStyle.addLine("Mensagem " + i);
        }

        inboxStyle.setSummaryText("Clique para exibir");
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(ctx)
            .setSmallIcon(R.drawable.ic_notificacao)
                .setColor(Color.RED)
                .setContentTitle("Notificação")
                .setContentText("Varios items pendentes")
                .setContentIntent(criarPendingIntent(ctx,"Mensagens nao lidas",-1))
                .setNumber(numero)
                .setStyle(inboxStyle);

        NotificationManagerCompat nm = NotificationManagerCompat.from(ctx);
        nm.notify(idNotificacao,mBuilder.build());
    }
}
