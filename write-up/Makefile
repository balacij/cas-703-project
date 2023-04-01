.PHONY: help system_requirements build debug clean open

help:
	@echo "Build:"
	@echo "  - build: Build a fresh copy of the 'write-up' in the build/ folder."
	@echo "  - debug: Same as 'build', but pauses build on errors for easier debugging."
	@echo "  - clean: Clean working TeX build artifacts."
	@echo ""
	@echo "Supplementary Information:"
	@echo "  - help               : View this help guide."
	@echo "  - system_requirements: List system requirements."

system_requirements:
	@echo "System Requirements: LaTeX (latexmk + LuaLaTeX)"

build: # standard, non-interactive, build
	latexmk -output-directory=build -pdflatex=lualatex -pdf -interaction=nonstopmode write-up.tex

debug: # for finding hard issues, this is an interactive version of 'build'
	latexmk -output-directory=build -pdflatex=lualatex -pdf write-up.tex

clean:
	rm -rf build/

open:
	xdg-open build/write-up.pdf
