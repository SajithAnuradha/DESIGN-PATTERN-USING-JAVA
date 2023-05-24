package Builder;

class BitRegister {
         public BitRegister(String s) {
                   System.out.println("generating bit register "+s);
         }
}

class SampledValue {
         public SampledValue(String s) {
                   System.out.println("generating sampled value "+s);
         }
}

class AnalogValue {
         public AnalogValue(String s) {
                  System.out.println("generating analog value "+s);
         }
}

class ControlSignal {
        public ControlSignal(String s) {
                System.out.println("generating control signal "+s);
    }
}
  
class Clock {
        public ControlSignal output() {
                return new ControlSignal("[clock]");
        }
}

    // Successive Approximation Register
class SAR {
    private Clock clk;
    private Comparator comp;
    
    public SAR(Clock clk, Comparator comp) {
         this.clk = clk; 
         this.comp = comp;
    }

    public BitRegister output() {
                 BitRegister br = comp.output();
                 ControlSignal cs = clk.output();
                 System.out.println("computing successive approximation register output");
    return new BitRegister("[SARout]");
    }
}





    // Digital to Analog Converter
class DAC {
    private SampledValue vref;
    private SAR sar;
    private boolean initialized;
    public DAC(SampledValue vref, SAR sar) {
               this.vref = vref; 
               this.sar = sar; 
               initialized = true;
    }
    // This constructor is for the initial creation of a DAC

    public DAC(SampledValue vref) {
                this.vref = vref; initialized = false;
    }

    public SampledValue output() {
            BitRegister br;
            if(initialized) br = sar.output();
            else br = new BitRegister("[initializing SAR]");

            System.out.println("computing D-to-A converter output");
            return new SampledValue("[DACout]");
    }
}


    // Sample and Hold
class SAH {
    private Clock clk;
    private AnalogValue ain;
    public SAH(Clock clk, AnalogValue ain) {
                this.clk = clk;
                this.ain = ain;
    }

    public SampledValue output() {
                         ControlSignal cs = clk.output();
                         System.out.println("computing sample-and-hold outout");
                         return new SampledValue("[Sout]");
    }

}

  
class Comparator {
    private SAH sah;
    private DAC dac;
    public Comparator(SAH sah, DAC dac) {
                  this.sah = sah;
                  this.dac = dac;
    }
    public BitRegister output() {
                    SampledValue sv1 = sah.output();
                    SampledValue sv2 = dac.output();
                    System.out.println("computing comparator output");
                   return new BitRegister("[Cout]");
    }
    }
    
class ShiftRegister {
    private Clock clk;
    private SAR sar;
    public ShiftRegister(Clock clk, SAR sar) {
                 this.clk = clk;
                 this.sar = sar;
    }
    public BitRegister output() {
                  BitRegister br = sar.output();
                  ControlSignal cs = clk.output();
                  System.out.println("computing shift register output");
                  return new BitRegister("[Dout]");
    }

}
 
    // This is the complex SAR-ADC object
    class SARADC {
    private Clock clk;
    private AnalogValue ain;
    private SampledValue vref;
    private SAH sah;
    private DAC dac0;
    private Comparator comp0;
    private SAR sar0;
    private DAC dac;
    private Comparator comp;
    private SAR sar;
    private ShiftRegister sr;
    // cont ...

    // cont ...
    public ShiftRegister buildComplexObject(AnalogValue ain) {
                clk = new Clock();
                this.ain = ain;
                vref = new SampledValue("[Vref]");
                sah = new SAH(clk,ain);
                dac0 = new DAC(vref);
                comp0 = new Comparator(sah,dac0);
                sar0 = new SAR(clk,comp0);
                dac = new DAC(vref,sar0);
                comp = new Comparator(sah,dac);
                sar = new SAR(clk,comp);
                sr = new ShiftRegister(clk,sar);
                return sr;
    }
    }
  
    // This is a Concrete ADC Builder for SAR-type ADC.
    // It builds the complex SARADC object using many
    // products/objects.
class SARADCBuilder implements ADCBuilder {
                        private SARADC sar;
                        private AnalogValue input;
                        private ShiftRegister output;
                        public void build(AnalogValue input) {
                        sar = new SARADC();
                        output = sar.buildComplexObject(input);
    }
    public BitRegister output() {
                 return output.output();
    }

}
    
    // This is the Abstract ADC Builder interface.
interface ADCBuilder {
       public void build(AnalogValue input);

       public BitRegister output();
    }
    // This is Client class (Director) interacting with a Concrete
    // Builder (SARADCBuilder) that is implementing the Abstract
    // Builder (ADCBuilder) interface.
public class TemperatureMonitor {
    public static void main(String [] args) {
    AnalogValue lm57 = new AnalogValue("[analog input signal]");
    // Let A-to-D Converter creation be handled by a Converter
    // Builder. This way, client program is completely isolated
    // from complex object construction required for building an
    // A-to-D Converter.
    ADCBuilder adcb = new SARADCBuilder();
    adcb.build(lm57);
    BitRegister br = adcb.output();
    }
    } 