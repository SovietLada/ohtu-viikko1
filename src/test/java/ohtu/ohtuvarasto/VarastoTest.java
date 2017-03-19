package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    Varasto täytettyVarasto;
    Varasto paradoksiVarasto;
    Varasto hassuVarasto;
    Varasto vikaVarasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
        täytettyVarasto = new Varasto(15, 10);
        paradoksiVarasto = new Varasto(-1);
        hassuVarasto = new Varasto(5, 10);
        vikaVarasto = new Varasto(-5, -5);
    }
    
    @Test
    public void kostruktoriLaittaaNegatiivisenSaldonOikein() {
        assertEquals(0, vikaVarasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void kostruktoriLaittaaNegatiivisenTilavuudenOikein() {
        // assertEquals(0, vikaVarasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(69, vikaVarasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void kostruktoriLaittaaSaldonOikein() {
        assertEquals(5, hassuVarasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void toStringToimii1() {
        assertEquals("saldo = 0.0, vielä tilaa 10.0", varasto.toString());
    }
    
    @Test
    public void toStringToimii2() {
        varasto.lisaaVarastoon(1.0);
        assertEquals("saldo = 1.0, vielä tilaa 9.0", varasto.toString());
    }
    
    @Test
    public void negatiivinenOttoEiToimi() {
        varasto.otaVarastosta(-1);
        assertEquals(10, täytettyVarasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenLisäysEiToimi() {
        varasto.lisaaVarastoon(-1);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenTilavuusEiTapahdu() {
        assertEquals(0, paradoksiVarasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriTäyttääOikein() {
        assertEquals(10, täytettyVarasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriAlustaaOikein() {
        assertEquals(15, täytettyVarasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriTäyttääTilan() {
        assertEquals(5, täytettyVarasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void eiOtaLiikaa() {
        varasto.otaVarastosta(11);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void eiLaitaLiikaa() {
        varasto.lisaaVarastoon(11);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

}