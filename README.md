# How to build?
Tests are part of the build
```bash 
git clone https://github.com/Witomir/webcrawler.git
cd webcrawler
docker build -t webcrawler:latest .
```

# How to run?
The URL `https://wiprodigital.com` is the default, so running the image without any
parameters will use that URL:
- `docker run webcrawler:latest`

To use any other URL pass it as a parameter to the command above:
- `docker run webcrawler:latest https://wbadowski.github.io/`

If you want to compile and run it on your machine:
```bash
./gradlew clean build --info
java -jar build/libs/webcrawler.jar -starting-url https://wiprodigital.com
```

# What it does and why?
Crawler works using breadth-first approach. 
It uses Guice for DI, Junit5 and Mockito for unit tests and Docker to run it on Java 11.

# What could be made better?
Although the test coverage is quite good, the tests themselves could be better/cover more cases.

It could also:
- consider some limits when fetching pages - a total pages/depth/time limit would be nice
- ignore pages differing by only last character `/`
- I didn't know how to treat anchor links with `#`, but that's a business requirement (program removes anchors from URLs) 
- handle page fetch errors
- recognize "static content" links better 
- crawl pages for logged-in users
- rendering could be swapped for something better
- for real life usage it could fetch pages in parallel
- and many more, but it takes time to do it!