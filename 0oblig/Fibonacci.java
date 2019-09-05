class Fibonacci {
  public static void main(String[] args) {
    Fibonacci f = new Fibonacci();
    long argument = Integer.parseInt(args[0]); // 2 Primitive oprasjoner: indexering av array, og setting av variabel
    if (argument < 0) argument = 0; // 2 Primitive oprasjoner: Sammenligning og setting av variabel.
    System.out.println("f(" + argument + ") = "
      + f.fibonacci(argument));
  }

  long fibonacci (long n) { // 1 primitiv oprasjon: n blir satt til argument.
    if (n <= 1) { // 1 Primitiv oprasjon, Sammenligning.
      return n; // 1 primitiv oprasjon
    } else {
      return  fibonacci (n - 1) + fibonacci (n - 2); // 3 primitive oprajoner: summing av n-1, summing av n-2 og summing av fibonacci(n-1) +fibonacci(n-2).
    }
  }
}
