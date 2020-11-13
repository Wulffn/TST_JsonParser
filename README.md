Mockito powerups:

How do you veryify that a mock was called?

Mockito har indbygget en verify metode, der fortæller hvor mange gange en metode kaldes. Denne kan assertes mod antallet af gange metoden ønskes kaldt.

How do you verify a mock was not called?

Mockito's fejlbeskeder i konsollen fortæller hvorvidt en metode er kaldt.

How do you specify how many times a mock should be called?

Verify metoden tager imod et argument, som kan specificere hvor mange gang metoden skal kaldes.

How do you verify that a mock was called with specific arguments?

        verify(storageMock, times(1))
                .createCustomer(
                        argThat(x -> x.getFirstname().equals(firstName) &&
                                x.getLastname().equals(lastName)));
                                
