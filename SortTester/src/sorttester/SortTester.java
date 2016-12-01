/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sorttester;

import java.io.File;
import java.io.PrintWriter;

/**
 *
 * @author jonathandavenport
 */
public class SortTester {

    public static void main(String[] args) throws Exception {

        //declare array to hold sizes of arrays of random integers to sort
        
        int[] allSizes = {100, 1000, 10000, 100000, 1000000, 10000000};
        
        //for iteratative sorters, smaller size array
        int[] smallSizes = {100, 1000, 10000, 100000};
        
        //array pointer to point to either array depending on the case
        int[] sizes;
        
        //declare array to hold test run durations
        double[] durations = new double[5];
        
        //declare longs to hold durations
        long startTime, endTime, duration;

        //open a file output stream
        File filename = new File("results.csv");
        PrintWriter outfile = new PrintWriter(filename);
        
        //print headers to file
        outfile.print("Sort Type,100 Items Avg,100 Items Best,100 Items Worst,"
                + "1000 Items Avg,1000 Items Best,1000 Items Worst,10000 Items Avg,"
                + "10000 Items Best,10000 Items Worst,100000 Items Avg,100000 Items Best,"
                + "100000 Items Worst,1000000 Items Avg,1000000 Items Best,1000000 Items Worst,"
                + "10000000 Items Avg,10000000 Items Best,10000000 Items Worst\n");
        
        //iterate through 5 sorting techniques
        for (int sortNum = 1; sortNum <= 5; sortNum++) {
            //switch between sorting algorithms. 1=quicksort, 2=mergesort,
            //3=bubblesort, 4=insertionsort, 5=selectionsort
            switch(sortNum) {
                case(1):
                    outfile.print("QuickSort,");
                    sizes = allSizes;
                    break;
                case(2):
                    outfile.print("MergeSort,");
                    sizes = allSizes;
                    break;
                case(3):
                    outfile.print("BubbleSort,");
                    sizes = smallSizes;
                    break;
                case(4):
                    outfile.print("InsertionSort,");
                    sizes = smallSizes;
                    break;
                case(5):
                    outfile.print("SelectionSort,");
                    sizes = smallSizes;
                    break;
                default:
                    sizes = allSizes;
                    break;
            }
            //iterate through array of sizes
            for (int size: sizes) {
                //do this 5 times
                for (int j = 0; j < 5; j++) {

                    //declare array to hold random integers    
                    int[] array = new int[size];
                    //fill array with random integers    
                    for (int i = 0; i < size; i++)
                        array[i] = 1 + (int) (Math.random() * 100000);
                    
                    //switch between sorting algorithms. 1=quicksort, 2=mergesort,
                    //3=bubblesort, 4=insertionsort, 5=selectionsort
                    switch(sortNum) {
                        case(1):
                            //record the system time
                            startTime = System.nanoTime();
                            //run the sort
                            quickSort(array, 0, array.length - 1);
                            //record the system time
                            endTime = System.nanoTime();
                            //calculate the duration
                            duration = endTime - startTime;
                            //print out the duration message
                            System.out.printf("QuickSort duration for %d was %12.8f %n", size, 
                            (double) duration / 1000000000);
                            //add the duration to the durations array
                            durations[j] = (double) duration / 1000000000;
                            break;
                        
                        case(2):
                            int[] temp = new int[size];
                            
                            startTime = System.nanoTime();

                            mergeSort(array, temp, 0, array.length - 1);

                            endTime = System.nanoTime();
                            duration = endTime - startTime;

                            System.out.printf("MergeSort duration for %d was %12.8f %n", size, 
                            (double) duration / 1000000000);

                            durations[j] = (double) duration / 1000000000;
                            break;
                        
                        case(3):
                            
                            if (size > 100000) break;
                            
                            startTime = System.nanoTime();

                            bubbleSort(array);

                            endTime = System.nanoTime();
                            duration = endTime - startTime;

                            System.out.printf("BubbleSort duration for %d was %12.8f %n", size, 
                            (double) duration / 1000000000);

                            durations[j] = (double) duration / 1000000000;
                            break;
                        
                        case(4):
                            
                            if (size > 100000) break;
                            
                            startTime = System.nanoTime();

                            insertionSort(array);

                            endTime = System.nanoTime();
                            duration = endTime - startTime;

                            System.out.printf("InsertionSort duration for %d was %12.8f %n", size, 
                            (double) duration / 1000000000);

                            durations[j] = (double) duration / 1000000000;
                            break;
                            
                        case(5):
                            
                            if (size > 100000) break;
                            
                            startTime = System.nanoTime();

                            selectionSort(array);

                            endTime = System.nanoTime();
                            duration = endTime - startTime;

                            System.out.printf("SelectionSort duration for %d was %12.8f %n", size, 
                            (double) duration / 1000000000);

                            durations[j] = (double) duration / 1000000000;
                            break;    
                            
                    }    
                    

                    

                }
                //print average duration to file
                outfile.print(findAverage(durations) + ",");
                //print minimum (best) duration to file    
                outfile.print(arrayMin(durations) + ",");
                //print maximum (worst) duration to file
                outfile.print(arrayMax(durations) + ",");        
                
                
            }
            outfile.print("\n");
        }
        
        outfile.close();



    } // end main()
    //**********************************************************************
    
    //a method to find the average of an array of durations
    public static double findAverage(double[] durations) {
        double total = 0;
        double average;
        for (double duration: durations) { //iterate through array
            total += duration;              //add each duration to the total
        }
        average = total / durations.length; //divide the total by the nummber of durations
        return average;
    }
    //************************************************************************
    //a method to find the maximum value in an array
    public static double arrayMax(double[] durations){
        double max = durations[0];
        for (double duration: durations) {
            //iterate through array and set max to current value if it is greater than max
            max = Math.max(duration, max);
        }
        return max;
    }
    //************************************************************************
    
    public static double arrayMin(double[] durations) {
        double min = durations[0];
        for (double duration: durations) {
            //iterate through array and set min to current value if it is less than min
            min = Math.min(duration, min);
        }
        return min;
    }
    //overloaded arrayMin to work with int arrays so that it can be used in selectionSort()
    public static int arrayMin(int[] durations, int startIndex, int endIndex) {
        int min = durations[startIndex]; //min value starts as the value at startIndex
        int minIndex = startIndex; //holds index of min value
        
        for (int i = startIndex + 1; i < durations.length; i++) {
            //iterate through array and set minIndex to i if current item value is less than min
            if (durations[i] < min) {
                minIndex = i;
            }
        }
        return minIndex;
    }
    //************************************************************************

    // a method to print the elements of an integer array on one line 
    public static void printtArray(int[] a) {
 
        // iterate and print the array on one line
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }

        System.out.println();

    } // end printArray()
    //************************************************************************

    // the recursive quicksort method, which calls the partition method
    public static void quickSort(int[] a, int startIndex, int endIndex) {
        int pivotIndex;      // the index of pivot returned by the quicksort partition

        // if the set has more than one element, then partition
        if (startIndex < endIndex) {
            // partition and return the pivotIndex
            pivotIndex = partition(a, startIndex, endIndex);
            // quicksort the low set
            quickSort(a, startIndex, pivotIndex - 1);
            // quiclsort the high set
            quickSort(a, pivotIndex + 1, endIndex);
        } // end if
    } // end quickSort()
    //************************************************************************

    // This method performs quicksort partitioning on a set of elements in an array.
    public static int partition(int[] a, int startIndex, int endIndex) {

        int pivotIndex;             // the index of the chosen pivot element
        int pivot;                  // the value of the chosen pivot
        int midIndex = startIndex;  // boundary element between high and low sets

        // select the center element in the set as the pivot by integer averaging
        pivotIndex = (startIndex + endIndex) / 2;
        pivot = a[pivotIndex];

        // put the pivot at the end of the set so it is out of the way
        swap(a, pivotIndex, endIndex);

        // iterate the set, up to but not including last element
        for (int i = startIndex; i < endIndex; i++) {
            // if a[i] is less than the pivot
            if (a[i] < pivot) {

                // put a[i] in the low half and increment current Index
                swap(a, i, midIndex);
                midIndex = midIndex + 1;
            } // end if
        } // end for 
        
        // partitioning complete -- move pivot from end to middle
        swap(a, midIndex, endIndex);

        // return index of pivot
        return midIndex;
        
    } // end partition
    //************************************************************************

    // This method swaps two elements in an integer array
    public static void swap(int[] a, int first, int second) {
        
        int c;  // a catalyst variable used for the swap

        c = a[first];
        a[first] = a[second];
        a[second] = c;

    } // end Swap()
    //************************************************************************
//*******************************************************************

    public static void mergeSort(int[] a, int[] temp, int low, int high) {
        //  low is the low index of the part of the array to be sorted
        //  high is the high index of the part of the array to be sorted
        
        int mid;  // the middle of the array – last item in low half
        
        // if high > low then there is more than one item in the list to be sorted
        if (high > low) {

            // split into two halves and mergeSort each part

            // find middle (last element in low half)   
            mid = (low+high)/2;
            mergeSort(a, temp, low, mid );
            mergeSort(a, temp, mid+1, high);
            
            // merge the two halves back together, sorting while merging
            merge(a, temp, low, mid, high);
        } // end if 
    }// end mergerSort()
    //********************************************************************
    
    
    /* This method merges the two halves of the set being sorted back together.
     * the low half goes from a[low] to a[mid]
     * the high half goes from a[mid+1] to a[high]
     * (High and low only refer to index numbers, not the values in the array.)
     * 
     * The work of sorting occurs as the two halves are merged back into one 
     * sorted set.
     * 
     * This version of mergesort copies the set to be sorted into the same 
     * locations in a temporary array, then sorts them as it puts them back.
     * Some versions of mergesort sort into the temporary array then copy it back.
     */
    public static void merge(int[] a, int[] temp, int low, int mid, int high) {
        //  low is the low index of the part of the array to be sorted
        //  high is the high index of the part of the array to be sorted
        //  mid is the middle of the array – last item in low half
        
        // copy the two sets from a[] to the same locations in the temporary array
        for (int i = low; i <= high; i++) {
            temp[i] = a[i];
        }

        //set up necessary pointers 
        int lowP = low;         // pointer to current item in low half
        int highP = mid + 1;    // pointer to current item in high half
        int aP = low;           // pointer to where each item will be put back in a[]

        // while the pointers have not yet reached the end of either half
        while ((lowP <= mid) && (highP <= high)) {

            // if current item in low half <= current item in high half 
            if (temp[lowP] <= temp[highP]) {
                // move item at lowP back to array a and increment low pointer
                a[aP] = temp[lowP];
                lowP++;
            } else {
                // move item at highP back to array a and increment high pointer
                a[aP] = temp[highP];
                highP++;
            } // end if..else
            
            // increment pointer for location in original array
            aP++;
        } // end while

        /* When the while loop is done, either the low half or the high half is done. 
         * We now simply move back everything in the half not yet done.
         * Remember, each half is already in order itself.
         */
        
        // if lowP has reached end of low half, then low half is done, move rest of high half
        if (lowP > mid) 
            for (int i = highP; i <= high; i++) {
                a[aP] = temp[i];
                aP++;
            } // end for
        else // high half is done, move rest of low half
        
            for (int i = lowP; i <= mid; i++) {
                a[aP] = temp[i];
                aP++;
            }// end for
    } // end merge()
    // *************************************************************
    
    public static void displayArray(int[] a) {

        for (int i = 0; i < a.length; i++) 
            System.out.print(a[i] + " ");
        System.out.println();
        
    } // end displayArray()
    // *************************************************************
    public static void bubbleSort(int[] array) {
        boolean sorted;
        //run this once- continue if !sorted
        do {
            sorted = true;
            //compare each item to the next item- if 1st is greater than 2nd, swap them
            for (int i = 0; i <= array.length - 2; i++) {
                if (array[i] > array[i+1]) {
                    int temp = array[i];
                    array[i] = array[i+1];
                    array[i+1] = temp;
                    //if swap has been made, run this loop again
                    sorted = false;
                }
            }

        } while (!sorted);

    }
    // *************************************************************
    public static void insertionSort(int[] array) {
        //in-place insertion sort method
        int nextItemToSort = 1; //index of item being inserted
        int endOfSorted = 0;    //index of last item in sorted part of the list
        int currentSorted;      //index of current item in the sorted list being compared
        int temp;               //holds value of item being inserted

        //iterate through array starting with second item
        for (int i = 1; i < array.length; i++) {
            
            boolean sorted = false;     //control variable for nested while loop
            currentSorted = endOfSorted; //first comparison is with the end of the sorted
                                        //loop; comparisons move to the left
            temp = array[nextItemToSort]; //copy the value of the current item 
                                        //being inserted to the temp variable

            while (!sorted) {

                /*if the comparison has already moved to the left of the array 
                (index = -1) or the temp value is greater than the currentSorted value
                insert the temp value to the right of the currentSorted value. 
                Then increment the values of nextItemToSort and endOfSorted
                
                */
                if (currentSorted == -1 || temp > array[currentSorted]) {
                    array[currentSorted + 1] = temp;
                    nextItemToSort++;
                    endOfSorted++;
                    sorted = true;
                    /*
                    for (int item: array)
                        System.out.print(item + " ");
                    System.out.println();
                    */
                } else {
                    //else shift the currentSorted value to the right, compare 
                    //the next value to the left to temp
                    array[currentSorted + 1] = array[currentSorted];
                    currentSorted--;
                }
            }
        }
    }
    
    //******************************************************************
    /*
    public static void selectionSort(int[] array) {
        int[] sortedArray = new int[array.length]; //new array to hold sorted values
        
        for (int i = 0; i < array.length; i++) {
            sortedArray[i] = arrayMin(array);//find the minimum value in the unsorted array
                                            // and copy it to the sorted array
        }
        
    }
    */
    public static void selectionSort(int[] array) {
        int minIndex; //holds index of item being sorted
        int temp; //holds value of item being sorted
        
        for (int i = 0; i < array.length; i++) {
            //find the min between indices i and the end of the array
            minIndex = arrayMin(array, i, array.length - 1);
            //copy the value to temp
            temp = array[minIndex];
            
            //iterate through array starting below minIndex and working left
            for (int j = minIndex - 1; j >= 0; j--) {
                array[j+1] = array[j]; //move values to the right
            }
            
            array[0] = temp; //put value being sorted at the front of the array
        }
}
} // end class SortTester
