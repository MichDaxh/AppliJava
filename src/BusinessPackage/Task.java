package BusinessPackage;

import ExceptionPackage.ConnectionException;
import ExceptionPackage.TaskException;
import ModelPackage.DataTaskModel;
import ModelPackage.ResultTaskModel;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class Task {
    private EventManager eventManager;
    private ArrayList<DataTaskModel> datas;
    private ArrayList<ResultTaskModel> outputs;

    public Task() {
        eventManager = new EventManager();
        datas = new ArrayList<DataTaskModel>();
        outputs = new ArrayList<ResultTaskModel>();
    }

    public ArrayList<ResultTaskModel> startTask() throws ConnectionException, TaskException {
        datas = eventManager.taskSearch();
        int savedYear;
        int i = 0;
        int cashPrizeTot;

        while (i < datas.size()){
            savedYear = datas.get(i).getYear();
            cashPrizeTot = 0;
            while(i < datas.size() && savedYear==datas.get(i).getYear()){
                cashPrizeTot += datas.get(i).getCashPrize();
                i++;
            }
            outputs.add(new ResultTaskModel(savedYear, cashPrizeTot));
        };
        outputs.sort(Comparator.comparingInt(ResultTaskModel::getYear));
        return outputs;
    }
}
