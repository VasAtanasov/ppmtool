import { createGlobalStyle } from 'styled-components/macro';

export default createGlobalStyle`

    *,
    *:before,
    *:after {
        margin: 0;
        padding: 0;
        border: 0;
        font-size: 100%;
        font: inherit;
        vertical-align: baseline;
        color: #000;
    }

    /* HTML5 display-role reset for older browsers */
    article, aside, details, figcaption, figure, 
    footer, header, hgroup, menu, nav, section {
        display: block;
    }
    
    body {
        line-height: 1;
        min-height: 100vh;
    }

    ol, ul {
        list-style: none;
    }
    
    blockquote, q {
        quotes: none;
    }

    blockquote:before, blockquote:after,
    q:before, q:after {
        content: '';
        content: none;
    }

    table {
        border-collapse: collapse;
        border-spacing: 0;
    }

    body {
        font-family:  'Titillium Web', 'Helvetica', 'Arial', 'sans-serif', sans-serif;
        font-size: 1rem;
        line-height: 1.4;
        font-weight: 400;
        background-color: #f4f4f4 !important;
        background-color: white !important;
        color: #333
    }

    code {
        font-family: source-code-pro, Menlo, Monaco, Consolas, 'Courier New',
        monospace;
    }

    a {
        -webkit-transition: .3s all ease;
        -o-transition: .3s all ease;
        transition: .3s all ease;
        color: #000;
    }

    a:hover, a:focus {
        text-decoration: none;
        outline: none !important;
    }

    h1, h2, h3, h4, h5,
    .h1, .h2, .h3, .h4, .h5 {
        line-height: 1.4;
        font-weight: 400;
    }
    
    img {
        width: 100%;
    }

    .navbar {
        background-color: #e3f2fd;
    }
    .fa.fa-edit {
        color: #18a2b9;
    }

    .list-group-item.delete:hover {
        cursor: -webkit-grab;
        background-color: pink;
    }

    .list-group-item.update:hover {
        cursor: -webkit-grab;
        background-color: gainsboro;
    }

    .list-group-item.board:hover {
        cursor: -webkit-grab;
        background-color: gainsboro;
    }

    .fa.fa-minus-circle {
        color: red;
    }

    .landing {
        position: relative;
        /* background: url("../img/showcase.jpg") no-repeat; */
        background-size: cover;
        background-position: center;
        height: 100vh;
        margin-top: -24px;
        margin-bottom: -50px;
    }

    .landing-inner {
        padding-top: 80px;
    }

    .dark-overlay {
        background-color: rgba(0, 0, 0, 0.7);
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
    }

    .card-form {
        opacity: 0.9;
    }

    .latest-profiles-img {
        width: 40px;
        height: 40px;
    }

    .form-control::placeholder {
        color: #bbb !important;
    }


`;
