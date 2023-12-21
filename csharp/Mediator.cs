// Mediator Interface (ILabInstrumentMediator): Defines communication between instruments.
// Concrete Mediator (LabInstrumentMediator): Manages and coordinates instrument interactions.
// Colleague Classes (Thermometer, PhMeter, Spectrometer): Represent different types of lab instruments.
// Data Logger: An additional component to receive processed data from the mediator.

// Mediator Interface
public interface ILabInstrumentMediator
{
    void RegisterInstrument(LabInstrument instrument);
    void ReceiveData(string data, LabInstrument sender);
    void SendDataToLogger(string data);
}

// Concrete Mediator
public class LabInstrumentMediator : ILabInstrumentMediator
{
    private List<LabInstrument> instruments = new List<LabInstrument>();
    private DataLogger logger = new DataLogger();

    public void RegisterInstrument(LabInstrument instrument)
    {
        instruments.Add(instrument);
    }

    public void ReceiveData(string data, LabInstrument sender)
    {
        Console.WriteLine($"Mediator: Processing data from {sender.GetType().Name}: {data}");
        // Example of processing and sending data to logger
        SendDataToLogger($"Processed: {data}");
    }

    public void SendDataToLogger(string data)
    {
        logger.LogData(data);
    }
}

// Colleague Classes
public abstract class LabInstrument
{
    protected ILabInstrumentMediator mediator;

    protected LabInstrument(ILabInstrumentMediator mediator)
    {
        this.mediator = mediator;
    }

    public abstract void SendData(string data);
    public abstract void ReceiveData(string data);
}

public class Thermometer : LabInstrument
{
    public Thermometer(ILabInstrumentMediator mediator) : base(mediator) {}

    public override void SendData(string data)
    {
        Console.WriteLine("Thermometer: Sending data.");
        mediator.ReceiveData(data, this);
    }

    public override void ReceiveData(string data)
    {
        Console.WriteLine("Thermometer: Received processed data.");
    }
}

// Similarly define classes for PhMeter, Spectrometer, etc.

// Data Logger
public class DataLogger
{
    public void LogData(string data)
    {
        Console.WriteLine($"DataLogger: Logging data - {data}");
    }
}
