import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Random;

class Stack{
    private final static String FILE_PATH = "stack.txt";
    private int top;
    private int data;
    private Stack[] stack;
    public Stack(){
        stack = new Stack[1];
        top = -1;
        readFile();
    }
    public Stack(int data,int top){
        stack = new Stack[1];
        this.data = data;
        this.top = top;
    }

    public int top(){
        if (top != -1){
            return stack[top].getData();
        }
        return 0;
    }

    public void push(int x){
        if (top == -1){
            data = x;
            Stack temp = new Stack(x,-1);
            stack[++top] = temp;
            writeFile();
        }else {
            if (top + 1 == stack.length){
                growSize();
            }
            Stack temp = new Stack(x,-1);
            stack[++top] = temp;
            writeFile();
        }
    }

    public Stack pop(){
        if (top == -1){
            return null;
        }else {
            Stack temp = stack[top];
            stack[top] = null;
            top--;
            writeFile();
            return temp;
        }
    }

    public void reverse(int k){
        if (stack != null && stack.length >= k){
            int[] temp = new int[k];
            for (int cycle = 0;cycle < k; cycle++){
                temp[cycle] = stack[top].data;
                pop();
            }
            for (int cycle = 0; cycle < k; cycle++){
                push(temp[cycle]);
            }
        }
    }
    public void growSize(){
        Stack[] temp;
        temp = new Stack[stack.length + 1];
        for (int i = 0; i < stack.length; i++){
            temp[i] = stack[i];
        }
        stack = temp;
    }

    public void readFile(){
        try {
            Path path1 = Paths.get(FILE_PATH);
            String absolutePath = path1.toAbsolutePath().toString();
            Path path = Paths.get(absolutePath);
            String line = Files.readAllLines(path).get(0);
            for (int i = line.split(" ").length -1; i >= 0; i--){
                int stack_element = Integer.parseInt(line.split(" ")[i]);
                push(stack_element);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void writeFile(){
        PrintStream ps0 = null;
        try {
            ps0 = new PrintStream(new FileOutputStream(FILE_PATH,false));
            ps0.print("");
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }finally {
            if (ps0 != null){
                ps0.flush();
                ps0.close();
            }
        }
        PrintStream ps = null;
        try {
            ps = new PrintStream(new FileOutputStream(FILE_PATH,true));
            for (int index = top ; index >= 0; index--){
                ps.print(stack[index].data+" ");
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }finally {
            if (ps != null){
                ps.flush();
                ps.close();
            }
        }
    }
    public void mergeSort(Stack[] array, int left, int right){
        if (left < right){
            int mid = (left + right) / 2;
            mergeSort(array, left,mid);
            mergeSort(array,mid + 1,right);
            merge(array,left,mid,right);
        }
    }
    public void merge(Stack[] array, int left, int mid, int right){
        int n1 = mid - left + 1;
        int n2 = right - mid;
        Stack[] leftArray = new Stack[n1];
        Stack[] rightArray = new Stack[n2];
        for (int i = 0; i < n1; i++){
            leftArray[i] = array[left+i];
        }
        for (int j = 0; j < n2; j++){
            rightArray[j] = array[mid+1+j];
        }
        int index_1 = 0, index_2 = 0, k = left;
        while (index_1 < n1 && index_2< n2){
            if (leftArray[index_1].getData() <= rightArray[index_2].getData()){
                array[k] = leftArray[index_1];
                index_1++;
            }else {
                array[k] = rightArray[index_2];
                index_2++;
            }
            k++;
        }
        while (index_1 < n1){
            array[k] = leftArray[index_1];
            index_1++;
            k++;
        }
        while (index_2 < n2){
            array[k] = rightArray[index_2];
            index_2++;
            k++;
        }
    }
    public int getTop(){
        return top;
    }

    public int getData(){
        return data;
    }

    public Stack[] getStack(){
        return stack;
    }



}

class Queue{
    private static final String FILE_PATH = "queue.txt";
    private int data;
    private int front;
    private int rear;
    private Queue[] queue;

    public Queue(){
        queue = new Queue[1];
        front = -1;
        rear = -1;
        readFile();
    }
    public Queue(int data){
        queue = new Queue[1];
        this.data = data;
        front = -1;
        rear = -1;
    }
    public void enqueue(int x){
        if (front == -1 && rear == -1){
            data = x;
            front++;
            Queue temp = new Queue(x);
            queue[++rear] = temp;
            writeFile();
        }else {
            if (rear + 1 == queue.length){
                growSize();
            }
            Queue temp = new Queue(x);
            queue[++rear] = temp;
            writeFile();
        }
    }

    public int front(){
        return queue[front].data;
    }

    public int rear(){
        return queue[rear].data;
    }

    public Queue dequeue(){
        if (front == -1){
            return null;
        }
        if (front == rear){
            Queue temp = queue[front];
            queue[front] = null;
            front = -1;
            rear = -1;
            writeFile();
            return temp;
        }else{
            Queue temp = queue[front];
            queue[front] = null;
            front++;
            writeFile();
            return temp;
        }
    }

    public void growSize(){
        Queue[] temp;
        temp = new Queue[queue.length + 1];
        for (int i = 0; i < queue.length; i++){
            temp[i] = queue[i];
        }
        queue = temp;
    }

    public void reverse(int k){
        if (queue != null && queue.length >= k){
            Stack tempStack = new Stack(0,-1);
            Queue tempQueue = new Queue(0);
            int temp_front = front;
            int temp_rear = rear;
            for (int index = temp_front;index <= temp_rear; index++){
                if (index < temp_front + k){
                    tempStack.push(dequeue().data);
                }else{
                    tempQueue.enqueue(dequeue().data);
                }
            }
            for (int i = temp_front; i < temp_front + k;i++){
                enqueue(tempStack.pop().getData());
            }
            for (int j = temp_front + k; j <= temp_rear; j++){
                enqueue(tempQueue.dequeue().data);
            }
            writeFile();
        }
    }

    public void mergeSort(Queue[] queue, int left, int right){
        if (left < right){
            int mid = (left + right) / 2;
            mergeSort(queue, left,mid);
            mergeSort(queue,mid + 1,right);
            merge(queue,left,mid,right);
        }
    }
    public void merge(Queue[] array, int left, int mid, int right){
        int n1 = mid - left + 1;
        int n2 = right - mid;
        Queue[] leftArray = new Queue[n1];
        Queue[] rightArray = new Queue[n2];
        for (int i = 0; i < n1; i++){
            leftArray[i] = array[left+i];
        }
        for (int j = 0; j < n2; j++){
            rightArray[j] = array[mid+1+j];
        }
        int index_1 = 0, index_2 = 0, k = left;
        while (index_1 < n1 && index_2< n2){
            if (leftArray[index_1].getData() <= rightArray[index_2].getData()){
                array[k] = leftArray[index_1];
                index_1++;
            }else {
                array[k] = rightArray[index_2];
                index_2++;
            }
            k++;
        }
        while (index_1 < n1){
            array[k] = leftArray[index_1];
            index_1++;
            k++;
        }
        while (index_2 < n2){
            array[k] = rightArray[index_2];
            index_2++;
            k++;
        }
    }

    public void readFile(){
        try {
            Path path1 = Paths.get(FILE_PATH);
            String absolutePath = path1.toAbsolutePath().toString();
            Path path = Paths.get(absolutePath);
            String line = Files.readAllLines(path).get(0);
            for (int i = 0; i < line.split(" ").length; i++){
                int queue_element = Integer.parseInt(line.split(" ")[i]);
                enqueue(queue_element);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void writeFile(){
        PrintStream ps0 = null;
        try {
            ps0 = new PrintStream(new FileOutputStream(FILE_PATH,false));
            ps0.print("");
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }finally {
            if (ps0 != null){
                ps0.flush();
                ps0.close();
            }
        }
        PrintStream ps = null;
        try {
            ps = new PrintStream(new FileOutputStream(FILE_PATH,true));
            if (front != -1 && rear != -1){
                for (int index = front ; index <= rear; index++){
                    ps.print(queue[index].data+" ");
                }
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }finally {
            if (ps != null){
                ps.flush();
                ps.close();
            }
        }
    }
    public int getData(){
        return data;
    }
    public Queue[] getQueue(){
        return queue;
    }
    public int getFront(){
        return front;
    }
    public int getRear(){
        return rear;
    }

}

class File{
    public static String[] readFile(String path){
        try {
            int index = 0;
            Path path1 = Paths.get(path);
            int length = Files.readAllLines(path1).size();
            String[] results = new String[length];
            for (String line : Files.readAllLines(path1)){
                results[index++] = line;
            }
            return results;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public static void writeFile(String path, String content, boolean append, boolean newLine){
        PrintStream ps = null;
        try {
            ps = new PrintStream(new FileOutputStream(path,append));
            ps.print(content + (newLine ? "\n" : ""));
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }finally {
            if (ps != null){
                ps.flush();
                ps.close();
            }
        }
    }

    public static void processCommands(String[] lines){
        Stack stack = new Stack();
        Queue queue = new Queue();
        if (lines != null){
            for (String line : lines){
                String command = line.split(" ")[1];
                switch (line.split(" ")[0]){
                    case "S":
                        switch (command){
                            case "removeGreater":
                                int k = Integer.parseInt(line.split(" ")[2]);
                                if (stack.getStack() != null){
                                    Stack temp = new Stack(0,-1);
                                    int cycle = stack.getTop();
                                    for (int index = 0; index <= cycle; index++){
                                        if (stack.top() <= k){
                                            temp.push(stack.top());
                                        }
                                        stack.pop();
                                    }
                                    cycle = temp.getTop();
                                    for (int index = 0; index <= cycle; index++){
                                        stack.push(temp.top());
                                        temp.pop();
                                    }
                                    stack.writeFile();
                                    if (stack.getStack() != null){
                                        writeFile("stackOut.txt","After "+command+" "+k+":",true,true);
                                        Stack tempstack = new Stack();
                                        for (int i = 0; i <= stack.getTop();i++){
                                            writeFile("stackOut.txt",tempstack.top()+" ",true,false);
                                            tempstack.pop();
                                        }
                                        stack.writeFile();
                                        writeFile("stackOut.txt","",true,true);
                                    }else {
                                        writeFile("stackOut.txt","After "+command+" "+k+":",true,true);
                                        writeFile("stackOut.txt","",true,true);
                                    }
                                }else{
                                    writeFile("stackOut.txt","After "+command+" "+k+":",true,true);
                                    writeFile("stackOut.txt","",true,true);
                                }
                                break;
                            case "calculateDistance":
                                int total_distance = 0;
                                Stack copyStack_1 = new Stack();
                                Stack copyStack_2 = new Stack();
                                int cycle_1 = copyStack_1.getTop();
                                if (stack.getStack() != null){
                                    for (int i = 0; i < cycle_1;i++){
                                        copyStack_2.pop();
                                        int cycle_2 = copyStack_2.getTop();
                                        int top_1 = copyStack_1.top();
                                        for (int j = 0; j <= cycle_2; j++){
                                            int top_2 = copyStack_2.top();
                                            int distance = top_1 - top_2;
                                            distance = distance < 0 ? -distance : distance;
                                            total_distance += distance;
                                            copyStack_2.pop();
                                        }
                                        copyStack_1.pop();
                                        for (int l = 0; l <= copyStack_1.getTop(); l++){
                                            copyStack_2.push(copyStack_1.getStack()[l].getData());
                                        }
                                    }
                                }
                                stack.writeFile();
                                writeFile("stackOut.txt","After "+command+":",true,true);
                                writeFile("stackOut.txt","Total distance="+total_distance,true,true);
                                break;
                            case "addOrRemove":
                                k = Integer.parseInt(line.split(" ")[2]);
                                if (k > 0){
                                    for (int cycle = 0; cycle < k; cycle++){
                                        Random random = new Random();
                                        int randomNumber = random.nextInt(51);
                                        stack.push(randomNumber);
                                    }
                                } else if (k < 0) {
                                        for (int cycle = 0; cycle < -k; cycle++){
                                            if (stack.getStack() != null){
                                                stack.pop();
                                            }
                                        }
                                }
                                stack.writeFile();
                                if (stack.getStack() != null) {
                                    Stack tempstack = new Stack();
                                    writeFile("stackOut.txt","After "+command+" "+k+":",true,true);
                                    for (int i = 0; i <= stack.getTop();i++){
                                        writeFile("stackOut.txt",tempstack.top()+" ",true,false);
                                        tempstack.pop();
                                    }
                                    stack.writeFile();
                                    writeFile("stackOut.txt","",true,true);
                                }else{
                                    writeFile("stackOut.txt","After "+command+" "+k+":",true,true);
                                    writeFile("stackOut.txt","",true,true);
                                }
                                break;
                            case "reverse":
                                k = Integer.parseInt(line.split(" ")[2]);
                                stack.reverse(k);
                                stack.writeFile();
                                if (stack.getStack() != null){
                                    Stack tempstack = new Stack();
                                    writeFile("stackOut.txt","After "+command+" "+k+":",true,true);
                                    for (int i = 0; i <= stack.getTop(); i++){
                                        writeFile("stackOut.txt",tempstack.top()+" ",true,false);
                                        tempstack.pop();
                                    }
                                    stack.writeFile();
                                    writeFile("stackOut.txt","",true,true);
                                }else {
                                    writeFile("stackOut.txt","After "+command+" "+k+":",true,true);
                                    writeFile("stackOut.txt","",true,true);
                                }
                                break;
                            case "sortElements":
                                if (stack.getStack() != null){
                                    Stack temp = new Stack();
                                    Stack[] sorted = new Stack[temp.getStack().length];
                                    int cycle = stack.getTop();
                                    for (int i = 0; i <= cycle;i++){
                                        sorted[i] = temp.getStack()[temp.getTop()];
                                        temp.pop();
                                    }
                                    stack.mergeSort(sorted,0,sorted.length-1);
                                    int count = stack.getTop();
                                    for (int i = 0; i <= count; i++){
                                        stack.pop();
                                    }
                                    for (Stack value : sorted) {
                                        stack.push(value.getData());
                                    }
                                    stack.reverse(stack.getTop()+1);
                                    stack.writeFile();
                                    Stack tempstack = new Stack();
                                    writeFile("stackOut.txt","After "+command+":",true,true);
                                    for (int i = 0; i <= stack.getTop();i++){
                                        writeFile("stackOut.txt",tempstack.top()+" ",true,false);
                                        tempstack.pop();
                                    }
                                    stack.writeFile();
                                    writeFile("stackOut.txt","",true,true);
                                }else{
                                    writeFile("stackOut.txt","After "+command+":",true,true);
                                    writeFile("stackOut.txt","",true,true);
                                }
                                break;
                            case "distinctElements":
                                int total_distinct_elements = 0;
                                if (stack.getStack() != null){
                                    Stack copy_1 = new Stack();
                                    Stack copy_2 = new Stack();
                                    int cycle1 = copy_1.getTop();
                                    for (int i = 0; i <= cycle1; i++){
                                        copy_2.pop();
                                        int top_1 = copy_1.top();
                                        boolean isDistinct = true;
                                        for (int j = 0; j <= copy_2.getTop();j++){
                                            int top_2 = copy_2.top();
                                            if (top_1 == top_2){
                                                isDistinct = false;
                                            }
                                            copy_2.pop();
                                        }
                                        if (isDistinct){
                                            total_distinct_elements++;
                                        }
                                        copy_1.pop();
                                        for (int l = 0; l < copy_1.getTop(); l++){
                                            copy_2.push(copy_1.getStack()[l].getData());
                                        }
                                    }
                                    stack.writeFile();
                                }
                                writeFile("stackOut.txt","After "+command+":",true,true);
                                writeFile("stackOut.txt","Total distinct element="+total_distinct_elements,true,true);
                                break;
                        }
                        break;
                    case "Q":
                        switch (command){
                            case "removeGreater":
                                int k = Integer.parseInt(line.split(" ")[2]);
                                if (queue.getQueue() != null){
                                    Queue temp = new Queue(0);
                                    int cycle = queue.getRear() - queue.getFront();
                                    for (int index = 0; index <= cycle; index++){
                                        if (queue.front() <= k){
                                            temp.enqueue(queue.front());
                                        }
                                        queue.dequeue();
                                    }
                                    cycle = temp.getRear() - temp.getFront();
                                    for (int index = 0; index <= cycle; index++){
                                        queue.enqueue(temp.front());
                                        temp.dequeue();
                                    }
                                    queue.writeFile();
                                    if (queue.getQueue() != null){
                                        writeFile("queueOut.txt","After "+command+" "+k+":",true,true);
                                        Queue tempQueue = new Queue();
                                        for (int i = queue.getFront(); i <= queue.getRear();i++){
                                            writeFile("queueOut.txt",tempQueue.front()+" ",true,false);
                                            tempQueue.dequeue();
                                        }
                                        queue.writeFile();
                                        writeFile("queueOut.txt","",true,true);
                                    }else {
                                        writeFile("queueOut.txt","After "+command+" "+k+":",true,true);
                                        writeFile("queueOut.txt","",true,true);
                                    }
                                }else{
                                    writeFile("queueOut.txt","After "+command+" "+k+":",true,true);
                                    writeFile("queueOut.txt","",true,true);
                                }
                                break;
                            case "calculateDistance":
                                int total_distance = 0;
                                Queue copyQueue_1 = new Queue();
                                Queue copyQueue_2 = new Queue();
                                int cycle_1 = copyQueue_1.getRear() - copyQueue_1.getFront();
                                if (queue.getQueue() != null){
                                    for (int i = 0; i < cycle_1;i++){
                                        copyQueue_2.dequeue();
                                        int cycle_2 = copyQueue_2.getRear() - copyQueue_2.getFront();
                                        int front_1 = copyQueue_1.front();
                                        for (int j = 0; j <= cycle_2; j++){
                                            int front_2 = copyQueue_2.front();
                                            int distance = front_1 - front_2;
                                            distance = distance < 0 ? -distance : distance;
                                            total_distance += distance;
                                            copyQueue_2.dequeue();
                                        }
                                        copyQueue_1.dequeue();
                                        for (int l = copyQueue_1.getFront(); l <= copyQueue_1.getRear(); l++){
                                            copyQueue_2.enqueue(copyQueue_1.getQueue()[l].getData());
                                        }
                                    }
                                }
                                queue.writeFile();
                                writeFile("queueOut.txt","After "+command+":",true,true);
                                writeFile("queueOut.txt","Total distance="+total_distance,true,true);
                                break;
                            case "addOrRemove":
                                k = Integer.parseInt(line.split(" ")[2]);
                                if (k > 0){
                                    for (int cycle = 0; cycle < k; cycle++){
                                        Random random = new Random();
                                        int randomNumber = random.nextInt(51);
                                        queue.enqueue(randomNumber);
                                    }
                                } else if (k < 0) {
                                    for (int cycle = 0; cycle < -k; cycle++){
                                        if (queue.getQueue() != null){
                                            queue.dequeue();
                                        }
                                    }
                                }
                                queue.writeFile();
                                if (queue.getQueue() != null) {
                                    Queue tempQueue = new Queue();
                                    writeFile("queueOut.txt","After "+command+" "+k+":",true,true);
                                    for (int i = queue.getFront(); i <= queue.getRear();i++){
                                        writeFile("queueOut.txt",tempQueue.front()+" ",true,false);
                                        tempQueue.dequeue();
                                    }
                                    queue.writeFile();
                                    writeFile("queueOut.txt","",true,true);
                                }else{
                                    writeFile("queueOut.txt","After "+command+" "+k+":",true,true);
                                    writeFile("queueOut.txt","",true,true);
                                }
                                break;
                            case "reverse":
                                k = Integer.parseInt(line.split(" ")[2]);
                                queue.reverse(k);
                                stack.writeFile();
                                if (queue.getQueue() != null){
                                    Queue tempQueue = new Queue();
                                    writeFile("queueOut.txt","After "+command+" "+k+":",true,true);
                                    for (int i = queue.getFront(); i <= queue.getRear(); i++){
                                        writeFile("queueOut.txt",tempQueue.front()+" ",true,false);
                                        tempQueue.dequeue();
                                    }
                                    queue.writeFile();
                                    writeFile("queueOut.txt","",true,true);
                                }else {
                                    writeFile("queueOut.txt","After "+command+" "+k+":",true,true);
                                    writeFile("queueOut.txt","",true,true);
                                }
                                break;
                            case "sortElements":
                                if (queue.getQueue() != null){
                                    Queue temp = new Queue();
                                    Queue[] sorted = new Queue[temp.getQueue().length];
                                    int cycle = temp.getRear() - temp.getFront();
                                    for (int i = 0; i <= cycle;i++){
                                        sorted[i] = temp.getQueue()[temp.getFront()];
                                        temp.dequeue();
                                    }
                                    queue.mergeSort(sorted,0,sorted.length-1);
                                    int count = queue.getFront() - queue.getRear();
                                    for (int i = 0; i <= cycle; i++){
                                        queue.dequeue();
                                    }
                                    for (Queue value : sorted) {
                                        queue.enqueue(value.getData());
                                    }
                                    queue.writeFile();
                                    Queue tempQueue = new Queue();
                                    writeFile("queueOut.txt","After "+command+":",true,true);
                                    for (int i = queue.getFront(); i <= queue.getRear();i++){
                                        writeFile("queueOut.txt",tempQueue.front()+" ",true,false);
                                        tempQueue.dequeue();
                                    }
                                    queue.writeFile();
                                    writeFile("queueOut.txt","",true,true);
                                }else{
                                    writeFile("queueOut.txt","After "+command+":",true,true);
                                    writeFile("queueOut.txt","",true,true);
                                }
                                break;
                            case "distinctElements":
                                int total_distinct_elements = 0;
                                if (queue.getQueue() != null){
                                    Queue copy_1 = new Queue();
                                    Queue copy_2 = new Queue();
                                    int cycle1 = copy_1.getRear() - copy_1.getFront();
                                    for (int i = 0; i <= cycle1; i++){
                                        if (copy_1.getFront() != -1 && copy_1.getRear() != -1){
                                            copy_2.dequeue();
                                            int top_1 = copy_1.front();
                                            boolean isDistinct = true;
                                            if (copy_2.getFront() != -1 && copy_2.getRear() != -1){
                                                int cycle2 = copy_2.getRear() - copy_2.getFront();
                                                for (int j = 0; j <= cycle2;j++){
                                                    int top_2 = copy_2.front();
                                                    if (top_1 == top_2){
                                                        isDistinct = false;
                                                    }
                                                    copy_2.dequeue();
                                                }
                                            }
                                            if (isDistinct){
                                                total_distinct_elements++;
                                            }
                                            copy_1.dequeue();
                                            if (copy_1.getFront() != -1 && copy_1.getRear() != -1){
                                                for (int l = copy_1.getFront(); l <= copy_1.getRear(); l++){
                                                    copy_2.enqueue(copy_1.getQueue()[l].getData());
                                                }
                                            }
                                        }
                                    }
                                    queue.writeFile();
                                }
                                writeFile("queueOut.txt","After "+command+":",true,true);
                                writeFile("queueOut.txt","Total distinct element="+total_distinct_elements,true,true);
                                break;
                        }
                        break;
                }
            }
        }
    }
}





public class Main {
    public static void main(String[] args) {
        String file_path;
        Path path = Paths.get(args[0]);
        file_path = path.toAbsolutePath().toString();
        File.processCommands(File.readFile(file_path));
    }
}