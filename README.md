Sampo
Prompt:
2. Ventana de Configuración Inicial 
○ Crear ventana para preparar la batalla antes de empezar 
○ Debe incluir: 
■ Panel de registro de jugadores 
● Campos: nombre del personaje, apodo, tipo de personaje 
● Validaciones (del 1° parcial) 
● Botón agregar personaje 
● Botón eliminar personaje 
● Mensajes de error en validaciones 
■ Panel de configuración de partidas 
● Vida inicial (editable, default: 100-160 aleatorio) 
● Fuerza inicial (editable, default: 15-25 aleatorio) 
● Defensa inicial (editable, default: 8-13 aleatorio) 
● Bendición inicial (editable, default: 30-100 aleatorio) 
● Cantidad de batallas (2, 3, 5) 
● Opcional: activar/desactivar sistema de ataques supremos 
○ Botones de control: 
■ Iniciar batalla → valida que haya 1 héroe y 1 villano, abre ventana de batalla 
■ Cargar Batalla Guardado → carga desde archivo 
■ Salir → cierra app

 Tengo que hacer esta interfaz en java con JForm podrias hacer un prototipo de interfaz con ese estilo?

Respuesta:<img width="758" height="495" alt="image" src="https://github.com/user-attachments/assets/7ac0d777-e3ee-424a-a0b6-fbc03f6d23bd" />

Valinotti:
Prompt:
Tengo que crear la siguiente ventana:
Ventana principal de juego 
○ Menú “Partida” 
■ Opcional: Pausar 
■ Guardar partida 
■ Salir ○ Menú “Ver” 
■ Historial de Partidas 
■ Estadísticas generales
■ Ranking de personajes 
○ Panel superior - Información de partida 
■ Batalla actual → “Partida 1/5” 
■ Turno actual → “Turno 4” 
○ Panel central - Estado de personajes 
■ Nombre y apodo 
■ Vida actual (opcional: con barra de progreso visual)
■ Porcentaje de bendición (opcional: con barra de progreso) 
■ Arma equipada 
■ Opcional: estado especial (casteando leviatán, aturdido, etc) 
■ Opcional: resaltar visualmente cuando un personaje está en estado crítico ○ Panel inferior ○ Log de eventos 
■ Mostrar eventos como: 
● “David invoca Espada Celestial” 
● “Goliath comienza a castear ‘Leviatán del Vacío’ (3 turnos restantes)” 
● “¡Ganador: David con 1 de vida!” 
● Opcional: implementar scroll automático 
○ A tener en cuenta: 
■ Detección de fin (personaje muerto o batallas completas) 
■ Transición a ventana de reportes

 Crea un prototipo para esa interfaz


Respuesta:<img width="671" height="677" alt="image" src="https://github.com/user-attachments/assets/51f4e815-ae7f-48a7-865d-0682ee38366c" />


Periotti:
Prompt 1:
○ Sección ranking: 
■ Tabla: Nombre | Apodo | Tipo | Vida Final | Victorias | Ataques Supremos Usados 
■ Ordenado por victorias descendente

Crea un prototipo de interfaz para esta vista.

Respuesta:<img width="673" height="677" alt="image" src="https://github.com/user-attachments/assets/79f8586a-c116-4036-a9ea-0382635944c3" />




Prompt 2:
○ Sección estadística: 
■ Mayor daño en un solo ataque (monto + jugador) 
■ Batalla más larga (turnos + ganador) 
■ Total de armas invocadas (por personaje) 
■ Ataques supremos ejecutados (total por cada personaje) 
■ Opcional: porcentaje de victorias por tipo

Ahora necesito un prototipo para esta vista.

Respuesta:<img width="668" height="660" alt="image" src="https://github.com/user-attachments/assets/a40d19f2-5a13-4af9-b32f-812f467acc22" />




Prompt 3:
○ Historial: 
■ Últimas 5 partidas 
■ Formato: "BATALLA #3 - Héroe: David | Villano: Goliath | Ganador: Goliath | Turnos: 6"

Por último hace un prototipo para esta vista.

Respuesta:<img width="674" height="671" alt="image" src="https://github.com/user-attachments/assets/22b5cc5c-54ca-4e2d-bfb9-20d248e52676" />

Gerhauser: https://chatgpt.com/share/69119605-b3d4-8008-a070-dc25669cd97a




