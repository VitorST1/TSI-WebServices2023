import json
from collections import defaultdict

NEWLINE_TWO_TABS = "\n\t\t"

with open("biblioteca.json", "r") as f:
    biblioteca = f.read()
    obj = json.loads(biblioteca)

    def obterLivros():
        livros = []
        for categoria in obj["livros"]:
            for livro in obj["livros"][categoria]:
                livros.append(livro)
        return livros

    def obterLivrosEmCategoria(categoria):
        return obj["livros"][categoria]

    def obterQuantidadeLivrosEmCategoria(categoria):
        livros = obterLivrosEmCategoria(categoria)
        quantidade = 0
        for livro in livros:
            quantidade += livro['copias']
        return quantidade

    def obterQuantidadeDeLivrosDiferentes():
        return len(livros)

    def obterQuantidadeDeLivrosTotais():
        quantidade = 0
        for livro in livros:
            quantidade += livro['copias']
        return quantidade

    def obterQuantidadeLivrosEmprestadosEmCategoria(categoria):
        livros = obterLivrosEmCategoria(categoria)
        quantidade = 0
        for livro in livros:
            quantidade += livro['emprestados']
        return quantidade

    def obterQuantidadeDeLivrosEmprestadosPorAutor():
        aux = defaultdict(int)
        for livro in livros:
            autor = livro['autor']
            emprestados = livro['emprestados']
            aux[autor] += emprestados

        dicionario_autor_emprestados = [{"autor": autor, "emprestados": emprestados} for autor, emprestados in aux.items()]
        return dicionario_autor_emprestados

    def obterAutorComMaisLivrosEmprestados():
        dicionario = obterQuantidadeDeLivrosEmprestadosPorAutor()
        autor = {"autor": "", "emprestados": 0}
        for d in dicionario:
            if d["emprestados"] > autor["emprestados"]:
                autor = d

        return autor['autor']

    def obterNomeLivroComMaisCopias():
        livroComMaisCopias = {'copias': 0}
        for livro in livros:
            if livro['copias'] > livroComMaisCopias['copias']:
                livroComMaisCopias = livro
        return livroComMaisCopias['titulo']

    def obterDicionarioAutorLivros():
        dictAutorLivros = {}
        for livro in livros:
            if(livro['autor'] in dictAutorLivros):
                dictAutorLivros[livro['autor']].append(livro['titulo'])
            else:
                dictAutorLivros[livro['autor']] = [livro['titulo']]

        return {k: sorted(v) for k,v in sorted(dictAutorLivros.items())}

    def obterAutorLivrosFormatado():
        s = ''
        for autor, livros in obterDicionarioAutorLivros().items():
            s += f'\t- {autor}:{NEWLINE_TWO_TABS}{NEWLINE_TWO_TABS.join(livros)}\n\n'
        return s

    def obterCategoriaComMaisLivros():
        categoriaComMaisLivros = {'copias': 0}
        for categoria in obj["livros"]:
            copias = obterQuantidadeLivrosEmCategoria(categoria)
            if  copias > categoriaComMaisLivros['copias']:
                categoriaComMaisLivros = {'copias': obterQuantidadeLivrosEmCategoria(categoria), 'categoria': categoria}
        return categoriaComMaisLivros['categoria']
        
    def obterLivroComMaiorTitulo():
        maiorTitulo = ''
        for livro in livros:
            if len(livro['titulo']) > len(maiorTitulo):
                maiorTitulo = livro['titulo']
        return maiorTitulo

    def obterAutorComMenorNome():
        autorComMenorNome = None
        for autor in obterDicionarioAutorLivros().keys():
            if autorComMenorNome is None or len(autor) < len(autorComMenorNome):
                autorComMenorNome = autor
        return autorComMenorNome

    livros = obterLivros()
        

    print(f'1 - Nome: {obj["informacao"]["nome"]}')
    print(f'2 - Quantidade de telefones: {len(obj["informacao"]["telefones"])}')
    print(f'3 - Quantidade de livros na categoria autoajuda: {obterQuantidadeLivrosEmCategoria("autoajuda")}')
    print(f'4 - Quantidade de títulos diferentes: {obterQuantidadeDeLivrosDiferentes()}')
    print(f'5 - Quantidade de títulos totais: {obterQuantidadeDeLivrosTotais()}')
    print(f'6 - Quantidade de livros emprestados na categoria romance: {obterQuantidadeLivrosEmprestadosEmCategoria("romance")}')
    print(f'7 - Autor com mais livros emprestados: {obterAutorComMaisLivrosEmprestados()}')
    print(f'8 - Livro com mais cópias: {obterNomeLivroComMaisCopias()}')
    print(f'9 - O nome de cada autor e nome de cada um dos seus livros:\n{obterAutorLivrosFormatado()}'.strip())
    print(f'10 - Categoria com mais livros: {obterCategoriaComMaisLivros()}')
    print(f'11 - Livro com o maior título: {obterLivroComMaiorTitulo()}')
    print(f'12 - Autor com o menor nome: {obterAutorComMenorNome()}')