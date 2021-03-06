package ec.vector.TracableDataTypes;

import java.util.ArrayList;
import java.util.List;



public class TraceableFloat{

    public TraceableFloat(){
        _value = 0;
        _traceVector = new ArrayList<TraceTuple>();
    }

    public TraceableFloat(float value, int initialTraceID){
        _value = value;
        _traceVector = new ArrayList<TraceTuple>();
        _traceVector.add(new TraceTuple(initialTraceID, 1.0));
    }

    public TraceableFloat(float value, List<TraceTuple> traceIDs){
        _value = value;
        _traceVector = new ArrayList<TraceTuple>();
        for(TraceTuple tt : traceIDs){
            _traceVector.add(tt);
        }
    }

    private float _value;
    private List<TraceTuple> _traceVector;

    public float getValue(){ return _value; }
    public List<TraceTuple> getTraceVector(){ return _traceVector; }

    public void setValue(float value, List<TraceTuple> traceVector){ this._value = value; this._traceVector = traceVector; }

    /**
     * builds a string out of the genome.
     * @return "[value, traceVector]" => "[value, [[traceID, impact]...]]"
     */
    public String toString(){
        String outString = "";
        outString += "["+_value+",";
        for(int i = 0; i < _traceVector.size(); i++){
            if(i>0)
                outString += ",";
            outString += _traceVector.get(i).toString();
        }
        outString += "]";
        return outString;
    }

    public void fromString(String data){

        //TODO: check if the input string has the right format!

        String splitter = ",\\["; //otherwise you get a unclosed brackets error...

        //remove the first and last character and split the string
        String[] parsedData = data.substring(1, data.length() - 2).split(splitter);
        _value = Float.parseFloat(parsedData[0]);
        _traceVector = new ArrayList<TraceTuple>();
        for(int i = 1; i < parsedData.length; i++){
            TraceTuple toAdd = new TraceTuple(0, 0.0); //these are dummy values which are overwritten in the next line
            toAdd.fromString("["+parsedData[i]); //re add the "["
            _traceVector.add(toAdd);
        }
    }
}
