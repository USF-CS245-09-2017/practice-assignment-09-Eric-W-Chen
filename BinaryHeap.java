import java.util.Arrays;
import java.util.NoSuchElementException;

public class BinaryHeap{
	public int[] heap;
	public int heapSize;

	public BinaryHeap(){
		heap = new int[10];
		heapSize = 0;
	}

	public boolean isEmpty(){
		return heapSize == 0;
	}

	public boolean isFull(){
		return heapSize == heap.length;
	}

	public void add(int item){
		if(isFull()){
			growHeap();
		}
		heap[heapSize++] = item;
		int curr = heapSize-1;
		int parent = (curr-1)/2;
		while(curr != 0 && heap[curr] < heap[parent]){
			swap(heap, curr, parent);
			curr = parent;
			parent = (parent-1)/2;
		}
	}

	public int remove(){
		if(isEmpty()){
			throw new NoSuchElementException();
		}
		swap(heap, 0, heapSize-1);
		--heapSize;
		if(heapSize > 0){
			siftDown(0);
		}
		return heap[heapSize];
	}

	void swap(int [] a, int i, int j){
		int temp;
		temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	private void siftDown(int n){
		int left = 2*n+1;
		int right = 2*n+2;
		int min = heap[0];
		if(right >= heapSize){
			if(left >= heapSize){
				return;
			}
			else{
				min = left;
			}
		}
		else{
			if(heap[left] <= heap[right]){
				min = left;
			}
			else{
				min = right;
			}
			if(heap[n] > heap[min]){
				swap(heap, min, n);
				siftDown(min);
			}
		}
	}

	private void growHeap(){
	heap = Arrays.copyOf(heap, heap.length*2);
	}
	
}