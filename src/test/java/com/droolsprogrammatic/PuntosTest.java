package com.droolsprogrammatic;

import com.droolsprogrammatic.modelos.Cliente;
import com.droolsprogrammatic.modelos.Compra;
import com.droolsprogrammatic.utilidades.KsessionBuilder;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.runtime.KieSession;

import static org.junit.Assert.assertEquals;

public class PuntosTest {

    KieSession kieSession;

    @Before
    public void init() {
        kieSession = new KsessionBuilder().createSession();
    }

    @Test
    public void sumarPuntosPorClienteVIP() {
        Cliente cliente = new Cliente(
        "Juan", true, 2000
        );
        Compra compra = new Compra(1000);

        kieSession.insert(cliente);
        kieSession.insert(compra);

        kieSession.fireAllRules();

        assertEquals(2100, cliente.getPuntos());
    }

    @Test
    public void noSumaPuntosNoCumplenTodosLosPatrones() {
        Cliente cliente = new Cliente(
                "Juan", true, 2000
        );
        kieSession.insert(cliente);
        kieSession.fireAllRules();

        assertEquals(2000, cliente.getPuntos());
    }

}
