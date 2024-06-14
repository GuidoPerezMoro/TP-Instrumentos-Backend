package utn.TpInstrumentosBackend.controllers;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.resources.preference.Preference;
import io.github.cdimascio.dotenv.Dotenv;
import utn.TpInstrumentosBackend.entities.Pedido;
import utn.TpInstrumentosBackend.entities.PreferenceMP;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MercadoPagoController {

    public PreferenceMP getPreferenciaIdMercadoPago(Pedido pedido){
        try {
            // Carga el archivo .env
            Dotenv dotenv = Dotenv.load();
            // Obtiene la variable de entorno MP_TOKEN
            String mpToken = dotenv.get("MP_TOKEN");

            MercadoPagoConfig.setAccessToken(mpToken);
            PreferenceItemRequest itemRequest = PreferenceItemRequest.builder()
                    .id(Long.toString(pedido.getId()))
                    .title("Pedido")
                    .description("Pedido realizado desde el carrito de compras")
                    .pictureUrl("https://img-global.cpcdn.com/recipes/0709fbb52d87d2d7/1200x630cq70/photo.jpg")
                    .quantity(1)
                    .currencyId("ARG")
                    .unitPrice(new BigDecimal(pedido.getTotalPedido()))
                    .build();
            List<PreferenceItemRequest> items = new ArrayList<>();
            items.add(itemRequest);

            PreferenceBackUrlsRequest backURL = PreferenceBackUrlsRequest.builder().success("http://localhost:8080/")
                    .pending("http://localhost:8080/").failure("http://localhost:8080").build();

            PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                    .items(items)
                    .backUrls(backURL)
                    .build();
            PreferenceClient client = new PreferenceClient();
            Preference preference = client.create(preferenceRequest);

            PreferenceMP mpPreference = new PreferenceMP();
            mpPreference.setStatusCode(preference.getResponse().getStatusCode());
            mpPreference.setId(preference.getId());
            return mpPreference;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
