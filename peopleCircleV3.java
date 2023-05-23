import java.util.*;
public class peopleCircleV3 {
    public static void main(String[] args)
    {
        System.out.println("Hello, please enter the number of people seating on this KILLING TABLE: ");
        int num = input.nextInt();
        int[] arr = new int[num];
        for(int i = 0 ; i <arr.length; i++)
            arr[i] = (i+1);

        System.out.printf("SURVIVOR: %2d",sur(arr));
    }
    static Scanner input = new Scanner(System.in);
    //The idea is as following, we've two arrays one is 'old' and one is 'nw', nw includes all alive people after a killing round. After complete 
    //a killing round 'nw' will be copied to 'old' then we make a new killing round.
    //each killing round kills either evens or odds according to a specific condition which is: if the current round killing evens then the next round will
    //kill evens if the number of people is odd, because last one will still alive then he would kill the first one in the next round(because first one is even, we consider the indexes not the number of that person)
    //otherwise the next round will kill odds. 





    public static int sur(int[] ar)
    {
        int[] old = new int[ar.length];
        int[] nw = new int[ar.length];
        boolean flag = false;
        int j = 0, count =0, result = 0 ;
        char last = 'o'; //by default is odd, because always in the first kill round the first (0) one will kill the second (1).
        arCopy(old, ar);
        

        while(flag == false)
        {

            //Storing Alive Persons In nw Array ;;;
            for(int i = 0 ; i<old.length ; i++)
            {
                if(last == 'o') //if the last one is odd then we will kill odds, and store evens
                {
                    if(i%2 == 0)    //if this person is EVEN INDEXED then store it
                    {
                        nw[j] = old[i];
                        j++;
                    }
                }else if(last == 'e') //if the last one is even then we will kill evens, and store odds
                {
                    if(i%2 != 0)    //if this person is ODD INDEXED then store it
                    {
                        nw[j] = old[i];
                        j++;
                    }
                }
            }

            //filling the array with -1's
            for(int o = j ; o < nw.length ; o++) //to assign all dead people with -1 ;;;
                if(nw[o] <= 0)
                    nw[o] = -1;

                //checking the last alive condition
                for(int o = 0 ; o < old.length ; o++)
                {
                    if(old[o] == -1)
                    {
                        if((o-1) % 2 == 0 && last != 'e') //if the last alive's index is even. and the last kill round was not go even by even, because if so, the last even will die, then the next turn, odds will die.
                            last = 'e'; 
                        else 
                            last = 'o';
                        break;
                    }else if(o ==old.length-1)
                    {
                        if(o % 2 == 0) //if the last alive's index is even.
                            last = 'e'; 
                        else 
                            last = 'o';
                        break;
                    }
                }

            //update
            arCopy(old, nw);
            j=0;
            count=0;

            //to count the alive people
            for(int o =0 ; o<nw.length ;o++)
            {
                if(nw[o] != -1)
                {
                    count++;
                    result = nw[o];
                }else 
                    continue;
            }

            //if there is only one alive person, then stop this kiiling loop
            if(count == 1)
                flag = true;
        }
        return result;

    }


    //A simple method to copy arrays
    public static int[] arCopy(int[] ar1, int[] ar2)
    {
        int length;
        if(ar1.length >= ar2.length)
            length = ar2.length;
        else
            length = ar1.length;
        for(int i = 0 ; i < length ; i++)
            ar1[i] = ar2[i];
        return ar1;
    } 
}