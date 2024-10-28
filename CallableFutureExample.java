import java.util.concurrent.*;

public class CallableFutureExample {
    public static void main(String[] args) {
        // create an ExecutorService with fixed thread pool
        ExecutorService executor = Executors.newFixedThreadPool(2);

        //create a Callable task
        Callable<Integer> callableTask = new Callable<Integer>(){
            @Override
            public Integer call() throws Exception{
                Thread.sleep(2000); // Simulate some long-running computation
                return 42; // Return the result of the computation
            }
        };

        // Submit the Callable task to the executor
        Future<Integer> future = executor.submit(callableTask);

        // Do some other work while the Callable is executing
        System.out.println("Do other work...");

        try{
            // Retrieve the result of the computation
            Integer result = future.get(); // This will block until the result is available
            System.out.println("Result from Callable: "+ result);
        }
        catch(InterruptedException | ExecutionException e){
            System.out.println("Error: "+ e.getMessage());
        }
        finally {
            // Shutdown the executor service
            executor.shutdown();
        }
    }
}
