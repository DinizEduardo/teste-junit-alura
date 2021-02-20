package br.com.caelum.leilao.dominio.teste;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestaLances {

    @Test
    public void testaSoUmLance() {
        Usuario steve = new Usuario("Steve jobs");

        Leilao leilao = new Leilao("PS5");

        leilao.propoe(new Lance(steve, 1000));

        assertEquals(1, leilao.getLances().size());

        assertEquals(1000, leilao.getLances().get(0).getValor(), 0.0001);
    }

    @Test
    public void testeDeveReceberVariosValores() {
        Usuario steve = new Usuario("Steve jobs");

        Usuario bill = new Usuario("Bill Gates");

        Leilao leilao = new Leilao("PS5");

        leilao.propoe(new Lance(steve, 1000));
        leilao.propoe(new Lance(bill, 2000));

        assertEquals(2, leilao.getLances().size());

        assertEquals(1000, leilao.getLances().get(0).getValor(), 0.0001);
        assertEquals(2000, leilao.getLances().get(1).getValor(), 0.0001);
    }

    @Test
    public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario() {
        Usuario steve = new Usuario("Steve jobs");

        Leilao leilao = new Leilao("PS5");

        leilao.propoe(new Lance(steve, 1000));
        leilao.propoe(new Lance(steve, 2000));

        assertEquals(1, leilao.getLances().size());
        assertEquals(1000, leilao.getLances().get(0).getValor(), 0.0001);
    }

    @Test
    public void naoDeveAceitarMaisDeCincoLancesDoMesmoUsuario() {
        Usuario steve = new Usuario("Steve Jobs");
        Usuario bill = new Usuario("Bill Gates");

        Leilao leilao = new Leilao("PS5");

        leilao.propoe(new Lance(steve, 1000));
        leilao.propoe(new Lance(bill, 2000));

        leilao.propoe(new Lance(steve, 3000));
        leilao.propoe(new Lance(bill, 4000));

        leilao.propoe(new Lance(steve, 5000));
        leilao.propoe(new Lance(bill, 6000));

        leilao.propoe(new Lance(steve, 7000));
        leilao.propoe(new Lance(bill, 8000));

        leilao.propoe(new Lance(steve, 9000));
        leilao.propoe(new Lance(bill, 10000));

        leilao.propoe(new Lance(steve, 11000));

        assertEquals(10, leilao.getLances().size());
        assertEquals(10000, leilao.ultimoLance().getValor(), 0.0001);

    }

}
