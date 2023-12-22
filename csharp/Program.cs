class Program
{
    static void Main(string[] args)
    {
        LabInstrumentMediator mediator = new LabInstrumentMediator();

        Thermometer thermometer = new Thermometer(mediator);
        // Other instruments like PhMeter, Spectrometer can also be instantiated.

        mediator.RegisterInstrument(thermometer);
        // Register other instruments.

        thermometer.SendData("Temperature: 22°C");
        // Other instruments can also send data.
        // Other
    }
}
