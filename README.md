# aplus-renamer
Renames A+ Computer Science source code files to be unique.

In my classroom, I used a few assignments from Stacey Armstrong's excellent
[A+ Computer Science](https://www.apluscompsci.com/ap_a_computer_science.htm) curriculum to help prepare them for the AP CS A exam.
(My district was already paying for a site license.)

However, my grading system required all filenames to be unique. And sometimes he would have files with the same name in
different chapters.

This program renames the files in a single folder and also edits the files themselves to update the references.

For example, assume that in chapter 11 there are files named `Triangle.java` and `TriangleRunner.java`:

```
// Triangle.java
public class Triangle {
   private int b, h;
   public Triangle() {
      this.b = this.h = 0;
   }
   public Triangle(int b, int h) {
      this.b = b;
      this.h = h;
   }
   // etc.
}
```

...and its associated driver file:

```
// TriangleRunner.java
public class TriangleRunner {
   Triangle test = new Triangle(6,7);
   test.foo();
   // etc.
}
```

My program renames `Triangle.java` to `Aplus11Triangle.java`, renames `TriangleRunner.java` to `Aplus11TriangleRunner.java`, and modifies the files like so:


```
// Aplus11Triangle.java
public class Aplus11Triangle {
   private int b, h;
   public Aplus11Triangle() {
      this.b = this.h = 0;
   }
   public Aplus11Triangle(int b, int h) {
      this.b = b;
      this.h = h;
   }
   // etc.
}

// Aplus11TriangleRunner.java
public class Aplus11TriangleRunner {
   Aplus11Triangle test = new Aplus11Triangle(6,7);
   test.foo();
   // etc.
}
```

Anyway, it has saved me a lot of time over the years.

-Graham
