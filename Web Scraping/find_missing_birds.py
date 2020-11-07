f = open("output.txt", "r")

content = f.readlines()
for line in content:
	if "Skipping" not in line:
		continue
	try:
		print(line[line.index("Skipping") + len("Skipping") + 1:])
	except Exception:
		continue				


