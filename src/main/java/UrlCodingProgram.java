import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class UrlCodingProgram {
    public static void main(String[] args) {
        var urlEncoded = """
                %3Cp%3E%D0%94%D0%BB%D1%8F%20%D1%80%D0%B5%D0%B0%D0%BB%D0%B8%D0%B7%D0%B0%D1%86%D0%B8%D0%B8%20%D0%BD%D0%BE%D0%B2%D1%8B%D1%85%20%D0%BF%D1%80%D0%BE%D0%B5%D0%BA%D1%82%D0%BE%D0%B2%20%D0%BC%D1%8B%20%D0%B8%D1%89%D0%B5%D0%BC%20%3Cstrong%3ESenior%20Full%20Stack%20Developer%3C/strong%3E%20%D1%81%20%D0%BE%D0%BF%D1%8B%D1%82%D0%BE%D0%BC%20%D1%80%D0%B0%D0%B1%D0%BE%D1%82%D1%8B%20%D0%BD%D0%B0%20%D0%B0%D0%BD%D0%B0%D0%BB%D0%BE%D0%B3%D0%B8%D1%87%D0%BD%D0%BE%D0%B9%20%D0%BF%D0%BE%D0%B7%D0%B8%D1%86%D0%B8%D0%B8.%3C/p%3E%20%3Cp%3E%3Cstrong%3E%D0%97%D0%B0%D0%B4%D0%B0%D1%87%D0%B8:%3C/strong%3E%3C/p%3E%20%3Cul%3E%20%3Cli%3E%20%3Cp%3E%D0%A3%D1%87%D0%B0%D1%81%D1%82%D0%B8%D0%B5%20%D0%B2%20%D0%BF%D1%80%D0%BE%D0%B5%D0%BA%D1%82%D0%B8%D1%80%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D0%B8%20%D0%B8%20%D1%81%D0%BE%D0%B7%D0%B4%D0%B0%D0%BD%D0%B8%D0%B8%20%D0%B0%D1%80%D1%85%D0%B8%D1%82%D0%B5%D0%BA%D1%82%D1%83%D1%80%D1%8B%3C/p%3E%20%3C/li%3E%20%3Cli%3E%20%3Cp%3E%D0%A0%D0%B0%D0%B7%D1%80%D0%B0%D0%B1%D0%BE%D1%82%D0%BA%D0%B0%20(frontend%20+%20backend)%3C/p%3E%20%3C/li%3E%20%3Cli%3E%20%3Cp%3E%D0%92%D0%B7%D0%B0%D0%B8%D0%BC%D0%BE%D0%B4%D0%B5%D0%B9%D1%81%D1%82%D0%B2%D0%B8%D0%B5%20%D1%81%D0%BE%20%D1%81%D0%BC%D0%B5%D0%B6%D0%BD%D1%8B%D0%BC%D0%B8%20%D0%BA%D0%BE%D0%BC%D0%B0%D0%BD%D0%B4%D0%B0%D0%BC%D0%B8%20(%D0%BA%D0%BE%D0%BC%D0%B0%D0%BD%D0%B4%D1%8B%20%D0%BC%D0%BE%D0%B1%D0%B8%D0%BB%D1%8C%D0%BD%D0%BE%D0%B9%20%D1%80%D0%B0%D0%B7%D1%80%D0%B0%D0%B1%D0%BE%D1%82%D0%BA%D0%B8)%3C/p%3E%20%3C/li%3E%20%3Cli%3E%20%3Cp%3E%D0%A2%D0%B5%D0%BA%D1%83%D1%89%D0%B8%D0%B9%20%D1%81%D1%82%D0%B5%D0%BA%20%D1%82%D0%B5%D1%85%D0%BD%D0%BE%D0%BB%D0%BE%D0%B3%D0%B8%D0%B9:%20JavaScript,%20Angular%202,%20NodeJS,%20PostgreSQL%3C/p%3E%20%3C/li%3E%20%3C/ul%3E%20%3Cp%3E%3Cstrong%3E%D0%9A%D0%B0%D0%BA%D0%B8%D0%B5%20%D0%BD%D0%B0%D0%B2%D1%8B%D0%BA%D0%B8%20%D0%B8%20%D1%83%D0%BC%D0%B5%D0%BD%D0%B8%D1%8F%20%D0%BD%D1%83%D0%B6%D0%BD%D1%8B:%3C/strong%3E%3C/p%3E%20%3Cul%3E%20%3Cli%3E%20%3Cp%3E%D0%9E%D0%BF%D1%8B%D1%82%20backend%20+%20frontend%20%D1%80%D0%B0%D0%B7%D1%80%D0%B0%D0%B1%D0%BE%D1%82%D0%BA%D0%B8%20%D0%BE%D1%82%203%20%D0%BB%D0%B5%D1%82%3C/p%3E%20%3C/li%3E%20%3Cli%3E%20%3Cp%3E%D0%9F%D0%BE%D0%BD%D0%B8%D0%BC%D0%B0%D0%BD%D0%B8%D0%B5%20%D0%B0%D1%80%D1%85%D0%B8%D1%82%D0%B5%D0%BA%D1%82%D1%83%D1%80%D1%8B%20%D0%B8%20%D0%BF%D1%80%D0%B8%D0%BD%D1%86%D0%B8%D0%BF%D0%BE%D0%B2%20%D1%80%D0%B0%D0%B7%D1%80%D0%B0%D0%B1%D0%BE%D1%82%D0%BA%D0%B8%20web-%D0%BF%D1%80%D0%B8%D0%BB%D0%BE%D0%B6%D0%B5%D0%BD%D0%B8%D0%B9%3C/p%3E%20%3C/li%3E%20%3Cli%3E%20%3Cp%3E%D0%9E%D0%BF%D1%8B%D1%82%20%D1%80%D0%B0%D0%B7%D1%80%D0%B0%D0%B1%D0%BE%D1%82%D0%BA%D0%B8%20%D0%B8%20%D0%BF%D1%80%D0%BE%D0%B5%D0%BA%D1%82%D0%B8%D1%80%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D1%8F%20%D1%80%D0%B5%D1%88%D0%B5%D0%BD%D0%B8%D0%B9%3C/p%3E%20%3C/li%3E%20%3C/ul%3E%20%3Cp%3E%3Cstrong%3E%D0%9C%D1%8B%20%D0%BF%D1%80%D0%B5%D0%B4%D0%BB%D0%B0%D0%B3%D0%B0%D0%B5%D0%BC:%3C/strong%3E%3C/p%3E%20%3Cul%3E%20%3Cli%3E%D0%9F%D1%80%D0%B8%D0%BD%D1%8F%D1%82%D1%8C%20%D1%83%D1%87%D0%B0%D1%81%D1%82%D0%B8%D0%B5%20%D0%B2%20%D0%BD%D0%BE%D0%B2%D0%BE%D0%BC%20%D0%BF%D1%80%D0%BE%D0%B5%D0%BA%D1%82%D0%B5%3C/li%3E%20%3Cli%3E%D0%9F%D0%BE%D0%BB%D0%BD%D0%BE%D1%81%D1%82%D1%8C%D1%8E%20%D1%83%D0%B4%D0%B0%D0%BB%D0%B5%D0%BD%D0%BD%D1%8B%D0%B9%20%D1%84%D0%BE%D1%80%D0%BC%D0%B0%D1%82%20%D1%80%D0%B0%D0%B1%D0%BE%D1%82%D1%8B%3C/li%3E%20%3Cli%3E%D0%A0%D0%B0%D0%B7%D0%B2%D0%B8%D1%82%D0%B8%D0%B5%20%D0%B2%20%D0%B8%D0%BD%D0%BE%D1%81%D1%82%D1%80%D0%B0%D0%BD%D0%BD%D0%BE%D0%B9%20%D0%BA%D0%BE%D0%BC%D0%BF%D0%B0%D0%BD%D0%B8%D0%B8%3C/li%3E%20%3C/ul%3E%20%3Cp%3E%D0%95%D1%81%D0%BB%D0%B8%20%D0%B2%D1%81%D1%91%20%D1%8D%D1%82%D0%BE%20%D0%BF%D1%80%D0%BE%20%D1%82%D0%B5%D0%B1%D1%8F%20%E2%80%94%20%D0%BE%D1%82%D0%BA%D0%BB%D0%B8%D0%BA%D0%B0%D0%B9%D1%81%D1%8F%20%D0%B8%20%D0%BD%D0%B0%D0%BF%D0%B8%D1%88%D0%B8%20%D0%BD%D0%B5%D0%BC%D0%BD%D0%BE%D0%B3%D0%BE%20%D0%BE%20%D1%81%D0%B2%D0%BE%D1%91%D0%BC%20%D0%BE%D0%BF%D1%8B%D1%82%D0%B5.%20%D0%91%D1%83%D0%B4%D0%B5%D0%BC%20%D1%80%D0%B0%D0%B4%D1%8B%20%D0%BF%D0%BE%D0%BE%D0%B1%D1%89%D0%B0%D1%82%D1%8C%D1%81%D1%8F!%3C/p%3E
                """;

        var urlDecoded = """
                <p>Для реализации новых проектов мы ищем <strong>Senior Full Stack Developer</strong> с опытом работы на аналогичной позиции.</p> <p><strong>Задачи:</strong></p> <ul> <li> <p>Участие в проектировании и создании архитектуры</p> </li> <li> <p>Разработка (frontend + backend)</p> </li> <li> <p>Взаимодействие со смежными командами (команды мобильной разработки)</p> </li> <li> <p>Текущий стек технологий: JavaScript, Angular 2, NodeJS, PostgreSQL</p> </li> </ul> <p><strong>Какие навыки и умения нужны:</strong></p> <ul> <li> <p>Опыт backend + frontend разработки от 3 лет</p> </li> <li> <p>Понимание архитектуры и принципов разработки web-приложений</p> </li> <li> <p>Опыт разработки и проектирования решений</p> </li> </ul> <p><strong>Мы предлагаем:</strong></p> <ul> <li>Принять участие в новом проекте</li> <li>Полностью удаленный формат работы</li> <li>Развитие в иностранной компании</li> </ul> <p>Если всё это про тебя — откликайся и напиши немного о своём опыте. Будем рады пообщаться!</p>
                """;

        var a = URLEncoder.encode(urlDecoded, StandardCharsets.UTF_8);
        var b = URLDecoder.decode(urlEncoded, StandardCharsets.UTF_8);
        var c = URLDecoder.decode(urlDecoded, StandardCharsets.UTF_8);
    }
}