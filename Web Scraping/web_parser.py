import time
from time import sleep
import selenium
from selenium import webdriver
import sys, os
import requests

f = open("content.txt", "r")
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.by import By
from selenium.common.exceptions import TimeoutException

#from selenium.webdriver.support.ui import Select
from contextlib import contextmanager
@contextmanager
def suppress_stdout():
    with open(os.devnull, "w") as devnull:
        old_stdout = sys.stderr
        sys.stderr = devnull
        try:  
            yield
        finally:
            sys.stderr = old_stdout
                                                            
from webdriver_manager.chrome import ChromeDriverManager
browser = None
with suppress_stdout():
	browser = webdriver.Chrome(ChromeDriverManager().install()) # Will install latest version or used cached version if already present.
	print ("\033[A                             \033[A")

species = []
with open("content.txt") as f:
	for line in f:
		species.append(line[line.index("'")+1:line.index("'", 2, len(line))])
# print(species)

results = {}
skipping = []
goodToGo = False
for bird in species:
	browser.get("https://www.google.com/search?q=are+"+bird+"+endangered")
	sleep(1)
	element = None
	try:
		element = browser.find_element_by_xpath("//*[@id=\"rso\"]/div[1]/div[1]/div[1]/div[1]/div/div[2]/div/div/div/div[1]/a")
	except Exception:
		try:
			element = browser.find_element_by_xpath("//*[@id=\"rso\"]/div[1]/div[1]/div[1]/div[1]/div/div[2]/div/div[1]/div/div/div[1]")
		except Exception:
			print("Skipping ", bird)
			skipping.append(bird)
			continue;
	print(bird, "is", element.text)
	results[bird] = element.text

print("Finished:", results)
print("Skipped:", skipping)

s = ""
for key, value in results.items():
	s += key + " : " + value + "\n"

s += "\n\n" + "\n".join(skipping)

f = open("results.txt", "x")
f.write(s)
f.close()
