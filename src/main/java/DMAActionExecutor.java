
import org.apache.oozie.action.ActionExecutor;
import org.apache.oozie.action.ActionExecutorException;
import org.apache.oozie.client.WorkflowAction;

public class DMAActionExecutor extends ActionExecutor {

  protected DMAActionExecutor(String type) {
    super(type);
    // TODO Auto-generated constructor stub
  }

  @Override
  public void start(Context context, WorkflowAction action)
      throws ActionExecutorException {
    // TODO Auto-generated method stub

  }

  @Override
  public void end(Context context, WorkflowAction action)
      throws ActionExecutorException {
    // TODO Auto-generated method stub

  }

  @Override
  public void check(Context context, WorkflowAction action)
      throws ActionExecutorException {
    // TODO Auto-generated method stub

  }

  @Override
  public void kill(Context context, WorkflowAction action)
      throws ActionExecutorException {
    // TODO Auto-generated method stub

  }

  @Override
  public boolean isCompleted(String externalStatus) {
    // TODO Auto-generated method stub
    return false;
  }

}
